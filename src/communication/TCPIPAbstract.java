package communication;

import config.ConfigData;
import config.ConfigInstance;
import pfg.config.Config;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.Socket;

public class TCPIPAbstract extends AbstractComm{

    protected int port;
    protected BufferedReader listeningData;
    protected PrintWriter sendingData;
    protected Thread listeningThread;
    protected final String synchronizedThread = "synchronized";
    private String messageToSend;
    private String receivedMessage;
    private Config config = ConfigInstance.getConfig();

    @Override
    /** Fonction permettant d'envoyer un order au client */
    public synchronized void send(Order order, boolean waitForCompletion, String... parameters)
    {
        //On forme le message
        this.messageToSend=order.getOrderStr();
        for (String param : parameters)
        {
            this.messageToSend += " " + param;
        }

        //Possède un auto-flush
        this.sendingData.println(this.messageToSend);

        //On attend le temps que l'ordre met pour se réaliser
        try
        {
            if (waitForCompletion) {
                Thread.sleep(order.getTimeToComplete());
            }
            else {
                Thread.sleep(config.getInt(ConfigData.MIN_TIME_BETWEEN_TWO_ORDERS));
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
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

