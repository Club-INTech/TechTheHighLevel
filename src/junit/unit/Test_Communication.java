package junit.unit;

import communication.AbstractComm;
import communication.CommunicationWrapper;
import communication.TCPIPClient;
import communication.TCPIPServer;
import org.junit.Test;

public class Test_Communication {

    private AbstractComm localhostClient;
    private AbstractComm localhostServer;
    private int nbMessages=10000;

    @SuppressWarnings("Duplicates")
    @Test
    public void visualCommunicationTest(){
        CommunicationWrapper commWrapper = new CommunicationWrapper(){
            @Override
            protected void openConnections() {
                localhostServer = new TCPIPServer(20000);
                addInterface(localhostServer);
                localhostClient = new TCPIPClient("localhost", 20000);
                addInterface(localhostClient);
                while (!areAllConnectionsReady()){
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            protected void handleMessage(String header, String message) {
                if (header.equals("CL")) {
                    System.out.print("Message received from client: ");
                    System.out.println(message);
                }
                else if (header.equals("SE")){
                    System.out.print("Message received from server: ");
                    System.out.println(message);
                }
                else {
                    System.out.println(message);
                }
            }
        };

        //On envoie 100 messages du client vers le serveur
        for (int i=0; i<this.nbMessages; i++){
            localhostClient.send("From client : "+i);
        }

        //On envoie 100 messages du serveur vers le client
        for (int i=0; i<this.nbMessages; i++){
            localhostServer.send("From server : "+i);
        }

        //On attend 1 seconde pour que les sockets affichent tous les messages
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("Duplicates")
    @Test
    public void booleanCommunicationTest(){

        StringBuilder referenceClientReceivedBuilder = new StringBuilder();
        StringBuilder referenceServerReceivedBuilder = new StringBuilder();

        for (int i=0; i<this.nbMessages; i++){
            referenceClientReceivedBuilder.append("CL");
            referenceClientReceivedBuilder.append(i);
            referenceServerReceivedBuilder.append("SE");
            referenceServerReceivedBuilder.append(i);
        }

        String referenceClientReceived=referenceClientReceivedBuilder.toString();
        String referenceServerReceived=referenceServerReceivedBuilder.toString();

        StringBuilder clientReceivedBuilder = new StringBuilder();
        StringBuilder serverReceivedBuilder = new StringBuilder();

        CommunicationWrapper commWrapper = new CommunicationWrapper(){
            @Override
            protected void openConnections() {
                localhostClient = new TCPIPClient("localhost", 20000);
                addInterface(localhostClient);
                localhostServer = new TCPIPServer(20000);
                addInterface(localhostServer);
                while (!areAllConnectionsReady()){
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            protected void handleMessage(String header, String message) {
                if (header.equals("CL")) {
                    clientReceivedBuilder.append(header);
                    clientReceivedBuilder.append(message);
                }
                else if (header.equals("SE")){
                    serverReceivedBuilder.append(header);
                    serverReceivedBuilder.append(message);
                }
            }
        };


        //On envoie 100 messages du client vers le serveur
        for (int i=0; i<this.nbMessages; i++){
            localhostClient.send("CL"+i);
        }

        //On envoie 100 messages du serveur vers le client
        for (int i=0; i<this.nbMessages; i++){
            localhostServer.send("SE"+i);
        }

        //On attend 1 seconde pour que les sockets affichent tous les messages
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        //On vérifie que les paquets arrivés correspondent bien à ce qui est attendu
        boolean serverToClient = false;
        boolean clientToServer = false;

        if (clientReceivedBuilder.toString().equals(referenceClientReceived)){
            serverToClient=true;
        }
        if (serverReceivedBuilder.toString().equals(referenceServerReceived)){
            clientToServer=true;
        }

        //Si tout correspond
        if (serverToClient && clientToServer) {
            System.out.println("Everything OK");
        }
        else{ //S'il y a une erreur
            if (!serverToClient && clientToServer){
                System.out.println("Problem serverToClient");
                System.out.println("Server reference:");
                System.out.println(referenceServerReceived);
                System.out.println("Server received:");
                System.out.println(serverReceivedBuilder.toString());
            }
            else if (serverToClient){
                System.out.println("Problem clientToServer");
                System.out.println("Server reference:");
                System.out.println(referenceClientReceived);
                System.out.println("Server received:");
                System.out.println(clientReceivedBuilder.toString());
            }
            else{
                System.out.println("Problem serverToClient and clientToServer");
                System.out.println("Server reference:");
                System.out.println(referenceServerReceived);
                System.out.println("Server received:");
                System.out.println(serverReceivedBuilder.toString());
                System.out.println("Client reference:");
                System.out.println(referenceClientReceived);
                System.out.println("Client received:");
                System.out.println(clientReceivedBuilder.toString());
            }
        }
    }
}

