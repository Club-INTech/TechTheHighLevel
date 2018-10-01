package communication;

import java.util.ArrayList;

/** Wrapper s'occupant de gérer l'établissement des connexions et le traitement des messages reçus
 * @author nayht
 */
public class CommunicationWrapper {

    protected ArrayList<AbstractComm> communicationInterfaces; //Liste des connexions instanciées (connectées ou non)
    private String lastMessage; //Dernier message reçu

    /** Fonction permettant d'initialiser les connexions*/
    protected void openConnections() {
        TCPIPClient localhost = new TCPIPClient("localhost", 23000);
        this.addCommunicationInterface(localhost);
    }

    /** Fonction s'occupant de gérer les messages reçus et de les distribuer aux thread de traitement */
    protected void handleMessage(String header, String message){
        System.out.println(header);
        System.out.println(message);
    }


    /** Fonction permettant d'ajouter une interface à la liste des interfaces */
    protected void addCommunicationInterface(AbstractComm interfaceToAdd){
        this.communicationInterfaces.add(interfaceToAdd);
    }

    /** Fonction permettant de lancer le listener de toutes les interfaces*/
    private void listenThread() {
        //On crée un thread de réceptions de données
        Thread readingThread = new Thread(() -> {
            //On boucle indéfiniment
            while (true)
            {
                //On parcourt chacune des connexions
                for (AbstractComm commInterface : communicationInterfaces){
                    try {
                        //On essaye de lire le buffer de réception de la connexion
                        lastMessage = commInterface.read();
                    } catch (ConnectionException e) {
                        lastMessage=null;
                        commInterface.setConnectionUp(false);
                        e.printStackTrace();
                    }
                    //Si on a reçu un message
                    if (lastMessage!=null) {
                        //On lance le traitement de ce message
                        handleMessage(lastMessage.substring(0,2),lastMessage.substring(2));
                    }

                }
            }
        });
        //On lance le listener
        readingThread.start();
    }

    /** Fonction permettant de savoir si toutes les connexions sont actives et établies*/
    protected boolean areAllConnectionsUp(){
        for (AbstractComm commInterface : communicationInterfaces){
            if (!commInterface.isConnectionUp()){
                return false;
            }
        }
        return true;
    }

    /** Constructeur */
    public CommunicationWrapper(){
        this.communicationInterfaces=new ArrayList<>();
        this.lastMessage=null;

        //On démarre les connexions
        openConnections();

        //On attend que toutes les connexions soient établies
        while (!areAllConnectionsUp()){
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //On lance le thread de réception des messages
        listenThread();
    }
}
