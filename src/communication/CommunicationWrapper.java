package communication;

import java.util.ArrayList;

/** Wrapper s'occupant de gérer l'établissement des connexions et le traitement des messages reçus
 * @author nayht
 */
public class CommunicationWrapper {

    protected ArrayList<AbstractConnection> communicationInterfaces; //Liste des connexions instanciées (connectées ou non)
    private String lastMessage; //Dernier message reçu

    /** Fonction permettant d'initialiser les connexions quand override */
    private void secureSetupAllConnections(){
        startAllConnections();
        while (!areAllConnectionsUp()){
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected void startAllConnections() {
        System.out.println("OVERRIDE CETTE PUTAIN DE METHODE : openAllConnections");
    }

    protected void startConnection(Connections connection){
        connection.establishConnection();
        this.addCommunicationInterface(connection.getConnection());
    }

    /** Fonction s'occupant de gérer les messages reçus et de les distribuer aux thread de traitement */
    protected void handleMessage(String header, String message){
        System.out.println(header);
        System.out.println(message);
    }


    /** Fonction permettant d'ajouter une interface à la liste des interfaces */
    private void addCommunicationInterface(AbstractConnection interfaceToAdd){
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
                for (AbstractConnection commInterface : communicationInterfaces){
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
    private boolean areAllConnectionsUp(){
        for (AbstractConnection commInterface : communicationInterfaces){
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
        secureSetupAllConnections();

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
