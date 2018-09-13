package communication;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.Socket;

public class TCPIPAbstract extends AbstractComm{

    protected int port;
    protected BufferedReader listeningData;
    protected PrintWriter sendingData;
    protected ListeningThread listeningThread;
    private String messageToSend;

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
        this.listeningThread = new ListeningThread(this.listeningData);
        listeningThread.start();
    }

    /** Constructeur */
    TCPIPAbstract(int port){
        this.port=port; //On spécifie uniquement le port sur lequel on attend les connexions
    }

    /** Listener */
    class ListeningThread extends Thread{
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
                    messageHandler(this.receivedMessage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void messageHandler(String message){
            //TRAITEMENT
            System.out.println(message);
            //on veut mettre les messages reçus dans des buffers, mais pour le moment on fait juste en sorte de print le résultat
            //FIN TRAITEMENT
        }

        @Override
        public void interrupt(){
            this.interrupt();
        }
    }
}

