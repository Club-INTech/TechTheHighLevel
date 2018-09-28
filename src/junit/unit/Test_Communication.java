package junit.unit;

import communication.AbstractComm;
import communication.TCPIPClient;
import communication.TCPIPServer;
import org.junit.Test;

public class Test_Communication {

    private AbstractComm localhostClient;
    private AbstractComm localhostServer;

    @Test
    public void testComm(){
        //On setup le serveur
        Thread a = new Thread() {
            @Override
            public void run() {
                localhostServer = new TCPIPServer(20000) {
                    @Override
                    public void messageHandler(String message) {
                        System.out.println("Message reçu SERVER SIDE: " + message); //On dit au thread de print tous les messages qu'il reçoit
                    }
                };
            }
        };

        //On setup le client
        Thread b = new Thread() {
            @Override
            public void run() {
                localhostClient = new TCPIPClient("localhost", 20000) {
                    @Override
                    public void messageHandler(String message) {
                        System.out.println("Message reçu CLIENT SIDE: " + message); //On dit au thread de print tous les messages qu'il reçoit
                    }
                };
            }
        };

        //On lance les threads
        a.start();
        b.start();

        //On attend que les interfaces se connectent entre elles
        try {
            a.join();
            b.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //On envoie 100 messages du client vers le serveur
        for (int i=0; i<100; i++){
            localhostClient.send("From client : "+i);
        }

        //On envoie 100 messages du serveur vers le client
        for (int i=0; i<100; i++){
            localhostServer.send("From server : "+i);
        }

        //On attend 1 seconde pour que les sockets affichent tous les messages
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

