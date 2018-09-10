package communication;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPIPServer extends AbstractComm{

    private int port;
    private BufferedReader listeningData;
    private PrintWriter sendingData;
    private String messageToSend;

    /** Fonction permettant d'accepter la première connexion venant sur le port spécifié */
    private void acceptConnection(){
        ServerSocket serverSocket;
        Socket connectionSocket;
        try {

            //On initialise le socket en mode serveur
            serverSocket = new ServerSocket(this.port);

            //Méthode bloquante
            connectionSocket = serverSocket.accept();

            //On définit les canaux d'entrée et de sortie
            this.listeningData = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            this.sendingData = new PrintWriter(new BufferedWriter(new OutputStreamWriter(connectionSocket.getOutputStream())),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    /** Fonction permettant d'envoyer un order au client */
    public void send(Order order, boolean waitForCompletion, String... parameters) {

        //On forme le message
        this.messageToSend=order.getOrderStr();
        for (String param : parameters){
            this.messageToSend += " " + param;
        }

        //Possède un auto-flush
        this.sendingData.println(this.messageToSend);

        //On attend le temps que l'ordre met pour se réaliser
        try {
            Thread.sleep(order.getTimeToComplete());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    /** Fonction permettant de lancer le listener */
    protected void listen() {
        ListeningThread listeningThread = new ListeningThread(this.listeningData);
        listeningThread.start();
    }

    /** Constructeur */
    TCPIPServer(int port){
        this.port=port; //On spécifie uniquement le port sur lequel on attend les connexions
        acceptConnection(); //On gère l'acceptation de la première connexion
        listen(); //On lance le listener
    }


    /** Listener */
    private class ListeningThread extends Thread{
        BufferedReader listeningData;
        String receivedMessage;

        ListeningThread(BufferedReader listeningData) {
            this.listeningData=listeningData;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    this.receivedMessage = this.listeningData.readLine();

                    //TRAITEMENT

                    //on veut mettre les messages reçus dans des buffers, mais pour le moment on fait juste en sorte de print le résultat
                    System.out.println(this.receivedMessage);


                    //FIN TRAITEMENT
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
