package junit.unit;

import communication.AbstractComm;
import communication.CommunicationWrapper;
import communication.TCPIPClient;
import communication.TCPIPServer;
import org.junit.Assert;
import org.junit.Test;

public class Test_Communication {

    private AbstractComm localhostClient;
    private AbstractComm localhostServer;
    private int nbMessages=100; //Nombre de message à envoyer pour tester la connexion

    @SuppressWarnings("Duplicates")
    @Test
    /** Test visuel */
    public void visualCommunicationTest(){
        //On crée le wrapper de communication en localhost
        CommunicationWrapper commWrapper = new CommunicationWrapper(){
            @Override
            /** On setup les connexions en localhost*/
            protected void openConnections() {
                //On définit le serveur et le client
                localhostServer = new TCPIPServer(20000);
                addCommunicationInterface(localhostServer);
                localhostClient = new TCPIPClient("localhost", 20000);
                addCommunicationInterface(localhostClient);

                //On attend que la connexion soit établie
                while (!areAllConnectionsUp()){
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            /** On traite les messages selon leurs headers*/
            protected void handleMessage(String header, String message) {
                if (header.equals("CL")) { //"CL" pour Client
                    System.out.print("Message received from client: ");
                    System.out.println(message);
                }
                else if (header.equals("SE")){ //"SE" pour Server
                    System.out.print("Message received from server: ");
                    System.out.println(message);
                }
                else {
                    System.out.println(message);
                }
            }
        };

        //On envoie les messages du client vers le serveur
        for (int i=0; i<this.nbMessages; i++){
            localhostClient.send("From client : "+i);
        }

        //On envoie les messages du serveur vers le client
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
    /** Test utilisable pour Jenkins */
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
        CommunicationWrapper commWrapper = new CommunicationWrapper(){
            @Override
            /** On setup les connexions en localhost*/
            protected void openConnections() {
                //On définit le serveur et le client
                localhostServer = new TCPIPServer(20000);
                addCommunicationInterface(localhostServer);
                localhostClient = new TCPIPClient("localhost", 20000);
                addCommunicationInterface(localhostClient);

                //On attend que la connexion soit établie
                while (!areAllConnectionsUp()){
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            /** On traite les messages selon leurs headers*/
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
            localhostClient.send("CL"+i);
        }

        //On envoie les messages du serveur vers le client
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
    }
}

