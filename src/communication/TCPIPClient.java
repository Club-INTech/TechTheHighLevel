package communication;

import java.io.*;
import java.net.*;


public class TCPIPClient extends AbstractComm{

    private int port;
    private String ip;
    private BufferedReader listeningData;
    private PrintWriter sendingData;
    private String messageToSend;


    private void connectTo(){
        Socket connectionSocket;
        try {
            //On crée la socket
            SocketAddress address;

            connectionSocket = new Socket();
            address = new InetSocketAddress(this.ip, this.port);


            while (!connectionSocket.isConnected()) {
                try {
                    connectionSocket.connect(address);
                }
                catch(IOException e){
                    connectionSocket = new Socket();
                    address = new InetSocketAddress(this.ip, this.port);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }

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
    public TCPIPClient(String ip, int port){
        this.ip = ip; //On spécifie l'IP sur laquelle on se connecte
        this.port = port; //On spécifie le port sur lequel on se connecte
        connectTo(); //On gère la demande de connexion
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
