package utils.communication;

import utils.Log;

import java.util.ArrayList;

/** Wrapper s'occupant de gérer l'établissement des connexions et le traitement des messages reçus
 * @author nayht
 */
public class ConnectionsManager {

    private ArrayList<AbstractConnection> communicationInterfaces; //Liste des connexions instanciées (connectées ou non)
    private String lastMessage; //Dernier message reçu
    private Thread readingThread;

    /** S'occupe de gérer les messages reçus et de les distribuer aux thread de traitement, peut être override*/
    protected void handleMessage(String header, String message){
        switch(header){
            case "DB": //Debug
                break;
            default:
                System.out.println(header);
                System.out.println(message);
        }
    }

    /** Fonction qui peut être override par les JUnits pour démarrer les connexions */
    public void startAllConnections(Connections... connections){
        if (connections.length>0) {
            for (Connections connection : connections) {
                //On démarre l'établissement d'une connexion
                connection.establishConnection();

                //On ajoute la connexion à la liste des connexions dont l'établissmenet a été lancé
                this.communicationInterfaces.add(connection.getConnection());
            }
        }
        else{
            Log.COMMUNICATION.critical("Aucune communications démarrées lors de l'instanciation du CommunicationWrapper");
        }

        //On attent que les connexions soient établies
        while (!areAllConnectionsUp()){
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //On lance le thread de réception des messages
        if (this.readingThread==null) {
            startReadingThread();
        }
    }

    /** Permet de lancer le listener de toutes les interfaces*/
    private void startReadingThread() {
        //On crée un thread de réceptions de données
        this.readingThread = new Thread(() -> {
            //On boucle indéfiniment
            while (!Thread.currentThread().isInterrupted())
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
        this.readingThread.start();
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

    /** Permet d'interrompre le thread d'écoute */
    public void stopReadingThread(){
        this.readingThread.interrupt();
    }

    /** Constructeur */
    public ConnectionsManager(){
        this.communicationInterfaces=new ArrayList<>();
        this.lastMessage=null;
    }
}
