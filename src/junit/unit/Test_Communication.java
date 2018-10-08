package junit.unit;

import communication.CommunicationWrapper;
import communication.Connections;
import data.controller.LidarHandlerRunnable;
import org.junit.After;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class Test_Communication {

    private int nbMessages=100; //Nombre de message à envoyer pour tester la connexion
    private CommunicationWrapper commWrapper;

    @After
    public void after(){
        this.commWrapper.stopListeningThread(); //On ferme le thread du comm wrapper qui écoute les messages reçus
    }

    @SuppressWarnings("Duplicates")
    @Test
    /* Test visuel */
    public void visualCommunicationTest(){

        //On crée le wrapper de communication en localhost
        this.commWrapper = new CommunicationWrapper(){
            @Override
            /* On setup les connexions en localhost */
            protected void startAllConnections() {
                startConnection(Connections.LOCALHOST_CLIENT);
                startConnection(Connections.LOCALHOST_SERVER);
            }

            @Override
            /* On traite les messages selon leurs headers */
            protected void handleMessage(String header, String message) {
                System.out.println(header+message);
            }
        };

        //On envoie les messages du client vers le serveur
        for (int i=0; i<this.nbMessages; i++){
            Connections.LOCALHOST_CLIENT.send("From client : "+i);
        }

        //On envoie les messages du serveur vers le client
        for (int i=0; i<this.nbMessages; i++){
            Connections.LOCALHOST_SERVER.send("From server : "+i);
        }

        //On attend 1 seconde pour que les sockets affichent tous les messages
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Close connections.
        Connections.LOCALHOST_CLIENT.close();
        Connections.LOCALHOST_SERVER.close();
    }


    @SuppressWarnings("Duplicates")
    @Ignore
    @Test
    public void lidarCommunicationTest(){
        System.out.println("Start test.");

        // Initialisation.
        LidarHandlerRunnable lidarHandlerRunnable = new LidarHandlerRunnable();
        Thread lidarHandlerThread = new Thread(lidarHandlerRunnable);
        System.out.println("Thread state : " + lidarHandlerThread.getState() + "\n");
        lidarHandlerRunnable.showLidarQueue();

        // Test queue.
        lidarHandlerRunnable.appendToQueue("FIRST");
        lidarHandlerRunnable.clearLidarQueue();

        // Starts the thread.
        lidarHandlerThread.start();
        System.out.println("Thread launched.");

        // On crée le wrapper de communication en localhost.
        this.commWrapper = new CommunicationWrapper(){
            @Override
            /* On setup les connexions en localhost */
            protected void startAllConnections() {
                startConnection(Connections.LIDAR_SOCKET);
            }

            @Override
            /* On traite les messages selon leurs headers */
            protected void handleMessage(String header, String message) {
                if (header.equals("CL")) { //"CL" pour Client
                    System.out.print("Message received from client: ");
                    System.out.println("CL: " + message);
                }
                else if (header.equals("SE")){ //"SE" pour Server
                    System.out.print("Message received from server: ");
                    System.out.println("SE: " + message);
                }
                else if (header.equals("LI")) {
                    System.out.print("Message received from lidar: ");
                    System.out.println("LI: " + message);
                    lidarHandlerRunnable.appendToQueue(message);
                }
                else {
                    System.out.println("LIDAR: " + message);
                    lidarHandlerRunnable.appendToQueue(message);
                }
            }
        };

        System.out.println("Wait...");


        // Waits.
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Stops the threads.
        lidarHandlerRunnable.stop();
        System.out.println("Thread stoped.");
        while (lidarHandlerThread.getState() != Thread.State.TERMINATED) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Thread state : " + lidarHandlerThread.getState() + "\n");
        lidarHandlerRunnable.clearLidarQueue();

        // Close connections.
        Connections.LIDAR_SOCKET.close();

    }


    @SuppressWarnings("Duplicates")
    @Test
    /* Test utilisable pour Jenkins */
    public void booleanCommunicationTest(){
        //On crée des StringBuilder pour définir les messages qu'on devrait recevoir
        StringBuilder referenceClientReceivedBuilder = new StringBuilder();
        StringBuilder referenceServerReceivedBuilder = new StringBuilder();

        //On définit les messages qu'on devrait recevoir
        for (int i=0; i<this.nbMessages; i++){
            referenceClientReceivedBuilder.append("CL");
            referenceClientReceivedBuilder.append(i);
            referenceServerReceivedBuilder.append("SE");
            referenceServerReceivedBuilder.append(i);
        }
        //On récupère sous forme de String les messages qu'on devrait recevoir
        String referenceClientReceived=referenceClientReceivedBuilder.toString();
        String referenceServerReceived=referenceServerReceivedBuilder.toString();

        //On crée les StringBuilder pour les messages qu'on reçoit
        StringBuilder clientReceivedBuilder = new StringBuilder();
        StringBuilder serverReceivedBuilder = new StringBuilder();

        //On crée le wrapper de communication en localhost
        this.commWrapper = new CommunicationWrapper(){
            @Override
            /* On setup les connexions en localhost*/
            protected void startAllConnections() {
                startConnection(Connections.LOCALHOST_CLIENT);
                startConnection(Connections.LOCALHOST_SERVER);
            }

            @Override
            /* On traite les messages selon leurs headers*/
            protected void handleMessage(String header, String message) {
                if (header.equals("CL")) { //"CL" pour Client
                    clientReceivedBuilder.append(header);
                    clientReceivedBuilder.append(message);
                }
                else if (header.equals("SE")){ //"SE" pour Server
                    serverReceivedBuilder.append(header);
                    serverReceivedBuilder.append(message);
                }
            }
        };

        //On envoie les messages du client vers le serveur
        for (int i=0; i<this.nbMessages; i++){
            Connections.LOCALHOST_CLIENT.send("CL"+i);
        }

        //On envoie les messages du serveur vers le client
        for (int i=0; i<this.nbMessages; i++){
            Connections.LOCALHOST_SERVER.send("SE"+i);
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

        //On assert pour Jenkins
        Assert.assertTrue((clientReceivedBuilder.toString().equals(referenceClientReceived)) &&
                (serverReceivedBuilder.toString().equals(referenceServerReceived)));

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

        //Close connections.
        Connections.LOCALHOST_CLIENT.close();
        Connections.LOCALHOST_SERVER.close();
    }
    
}

