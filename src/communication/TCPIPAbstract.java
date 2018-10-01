package communication;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.Socket;

/** Classe gérant l'envoi et la réceptionde données sur une connexion TCP IP établie
 * @author nayht
 */
public class TCPIPAbstract extends AbstractComm{

    protected String ip;
    protected int port;
    protected Socket socket;
    protected BufferedReader listeningData;
    protected PrintWriter sendingData;

    @Override
    /** Fonction permettant d'envoyer un order au client */
    public synchronized void send(String message)
    {
        //Possède un auto-flush
        this.sendingData.println(message);
    }

    /** Fonction permettant de savoir si le buffer de réception contient quelque chose*/
    private boolean hasReceivedSomething(){
        try {
            return this.listeningData.ready();
        } catch (IOException e) {
            this.setConnectionUp(false);
            return false;
        }
    }

    @Override
    /** Fonction permettant de lire le buffer de réception*/
    public String read() throws ConnectionException {
        try
        {
            //On synchronise au cas où on fermerait le thread
            synchronized (this) {
                if (this.isConnectionUp()) {
                //Si la connexion a été établie
                    //Si on le buffer de réception contient quelque chose
                    if (this.hasReceivedSomething()) {
                        //On renvoie la dernière ligne arrivée
                        return listeningData.readLine();
                    }
                    else{
                        //Si le buffer de reception est vide, on renvoie null
                        return null;
                    }
                }
                else{
                    return null;
                }
            }
        }
        catch (IOException e) {
            throw new ConnectionException("Connection got IOException");
        }
    }

    /** Fonction permettant de fermer la socket proprement */
    public void close(){
        try {
            //On synchronise au cas où on lit une donnée
            synchronized (this) {
                this.socket.close(); //On ferme le socket
                this.setConnectionUp(false);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Constructeur */
    TCPIPAbstract(int port){
        this.port=port; //On spécifie uniquement le port sur lequel on attend les connexions
    }
}

