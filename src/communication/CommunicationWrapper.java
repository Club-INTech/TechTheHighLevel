package communication;

import java.util.ArrayList;

public class CommunicationWrapper {

    protected ArrayList<AbstractComm> communicationInterfaces;
    private String lastMessage;


    /** Fonction permettant d'initialiser les connexions*/
    protected void openConnections() {
        TCPIPClient localhost = new TCPIPClient("localhost", 23000);
        this.addInterface(localhost);
    }

    protected void addInterface(AbstractComm interfaceToAdd){
        this.communicationInterfaces.add(interfaceToAdd);
    }

    protected void handleMessage(String header, String message){
        System.out.println(header);
        System.out.println(message);
    }

    /** Fonction permettant de lancer le listener de toutes les interfaces*/
    private void listenThread() {
        /** Listener */
        Thread readingThread = new Thread(() -> {
            while (true)
            {
                for (AbstractComm commInterface : communicationInterfaces){
                    if (commInterface.isConnectionUp()) {
                        try {
                            if (commInterface.hasReceivedSomething()) {
                                lastMessage = commInterface.read();
                            }
                            else {
                                lastMessage = null;
                            }
                        } catch (ConnectionException e) {
                            lastMessage=null;
                            commInterface.setConnectionUp(false);
                            e.printStackTrace();
                        }
                    }
                    if (lastMessage!=null) {
                        handleMessage(lastMessage.substring(0,2),lastMessage.substring(2));
                    }

                }
            }
        });
        //On lance le listener
        readingThread.start();
    }

    public boolean areAllConnectionsReady(){
        for (AbstractComm commInterface : communicationInterfaces){
            if (!commInterface.isConnectionUp()){
                return false;
            }
        }
        return true;
    }


    public CommunicationWrapper(){
        this.communicationInterfaces=new ArrayList<>();
        this.lastMessage=null;
        openConnections();
        while (!areAllConnectionsReady()){
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        listenThread();
    }
}
