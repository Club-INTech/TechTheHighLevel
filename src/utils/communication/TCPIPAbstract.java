package utils.communication;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;

public class TCPIPAbstract extends AbstractComm{

    protected int port;
    protected BufferedReader listeningData;
    protected PrintWriter sendingData;
    protected Thread listeningThread;
    protected final String synchronizedThread = "synchronized";
    private String messageToSend;
    private String receivedMessage;

    @Override
    /** Fonction permettant d'envoyer un order au client */
    public synchronized void send(String orderStr, String... parameters)
    {
        //On forme le message
        this.messageToSend=orderStr;
        for (String param : parameters)
        {
            this.messageToSend += " " + param;
        }

        //Possède un auto-flush
        this.sendingData.println(this.messageToSend);
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
                        synchronized (synchronizedThread)
                        {
                            if (!Thread.currentThread().isInterrupted())
                            {
                                receivedMessage = listeningData.readLine();
                            }
                            else
                            {
                                break;
                            }
                        }
                        messageHandler(receivedMessage);
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        };
        this.listeningThread.start();
    }

    /** Constructeur */
    TCPIPAbstract(int port){
        this.port=port; //On spécifie uniquement le port sur lequel on attend les connexions
    }
}

