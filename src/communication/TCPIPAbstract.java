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

    public boolean hasReceivedSomething(){
        try {
            return this.listeningData.ready();
        } catch (IOException e) {
            this.setConnectionUp(false);
            return false;
        }
    }

    @Override
    public String read() throws ConnectionException {
        try
        {
            //On synchronise au cas où on fermerait le thread
            synchronized (this) {
                if (!Thread.currentThread().isInterrupted()) {
                    return listeningData.readLine();
                }
                else{
                    throw new ConnectionException("Conenction has been interrupted");
                }
            }
        }
        catch (IOException e) {
            throw new ConnectionException("Connection get IOException");
        }
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

