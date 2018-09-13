package communication;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.Socket;

public class TCPIPAbstract extends AbstractComm{

    protected int port;
    protected BufferedReader listeningData;
    protected PrintWriter sendingData;
    protected Thread listeningThread;
    private String messageToSend;
    private String receivedMessage;

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
        /** Listener */
        this.listeningThread = new Thread(){
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        receivedMessage = listeningData.readLine();
                        messageHandler(receivedMessage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        listeningThread.start();
    }

    /** Constructeur */
    TCPIPAbstract(int port){
        this.port=port; //On spécifie uniquement le port sur lequel on attend les connexions
    }
}

