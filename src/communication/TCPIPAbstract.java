package communication;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.Socket;

public class TCPIPAbstract extends AbstractComm{

    protected String ip;
    protected int port;
    protected Socket socket;
    protected BufferedReader listeningData;
    protected PrintWriter sendingData;
    protected Thread listeningThread;
    private String receivedMessage;

    @Override
    /** Fonction permettant d'envoyer un order au client */
    public synchronized void send(String message)
    {
        //Possède un auto-flush
        this.sendingData.println(message);
    }

    @Override
    /** Fonction permettant de lancer le listener */
    protected void listen() {
        /** Listener */
        this.listeningThread = new Thread(){
            @Override
            public void run()
            {
                while (true)
                {
                    try
                    {
                        //On synchronise au cas où on fermerait le thread
                        synchronized (this) {
                            if (!Thread.currentThread().isInterrupted()) {
                                receivedMessage = listeningData.readLine();
                            } else {
                                break;
                            }
                        }
                        //On transmet le message reçu au messageHandler
                        messageHandler(receivedMessage);
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        };

        //On lance le listener
        this.listeningThread.start();
    }

    /** Fonction permettant de fermer la socket proprement */
    public void close(){
        try {
            //On synchronise au cas où on lit une donnée
            synchronized (this) {
                //On arrête le thread d'écoute en lançant une interruption
                this.listeningThread.interrupt();
            }
            this.socket.close(); //On ferme le socket
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Constructeur */
    TCPIPAbstract(int port){
        this.port=port; //On spécifie uniquement le port sur lequel on attend les connexions
    }
}

