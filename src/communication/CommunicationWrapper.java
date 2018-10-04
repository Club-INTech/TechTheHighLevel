package communication;

import utils.Log;

import java.util.ArrayList;

/** Wrapper s'occupant de gérer l'établissement des connexions et le traitement des messages reçus
 * @author nayht
 */
public class CommunicationWrapper {

    protected ArrayList<AbstractConnection> communicationInterfaces; //Liste des connexions instanciées (connectées ou non)
    private String lastMessage; //Dernier message reçu

    /** Permet d'initier toutes les connexions quand override */
    protected void startAllConnections() {
        Log.COMMUNICATION.critical("OVERRIDE CETTE PUTAIN DE METHODE : openAllConnections");
    }

    /** Permet de démarrer l'établissement d'une connexion et de l'ajouter à la liste des connexions ouvertes */
    protected void startConnection(Connections connection){
        //On démarre l'établissement d'une connexion
        connection.establishConnection();

        //On ajoute la connexion à la liste des connexions dont l'établissmenet a été lancé
        this.addCommunicationInterface(connection.getConnection());
    }

    /** S'occupe de gérer les messages reçus et de les distribuer aux thread de traitement, peut être override*/
    protected void handleMessage(String header, String message){
        System.out.println(header);
        System.out.println(message);
    }

    /** Permet de démarrer et de finir l'établissement de toutes les connexions,
     * avec un aspect bloquant tant que toutes les connexions ne sont pas établies*/
    private void secureSetupAllConnections(){
        startAllConnections(); //On démarre les connexions

        //On attent que les connexions soient établies
        while (!areAllConnectionsUp()){
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /** Permet d'ajouter une interface à la liste des interfaces */
    private void addCommunicationInterface(AbstractConnection interfaceToAdd){
        this.communicationInterfaces.add(interfaceToAdd);
    }

    /** Permet de lancer le listener de toutes les interfaces*/
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

    /** Permet de savoir si toutes les connexions sont actives et établies*/
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
