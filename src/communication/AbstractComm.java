package communication;


/** Classe mère de toutes les interfaces de connexion
 * @author nayht
 */
public abstract class AbstractComm {

    private boolean connectionUp=false; //Permet de savoir si la connexion est active

    /** Permet d'envoyer un message */
    public abstract void send(String string);

    /** Permet de lire un message reçu */
    public abstract String read() throws ConnectionException;

    /** Getter pour savoir si la connxion est active */
    protected boolean isConnectionUp(){
        return this.connectionUp;
    }

    /** Setter pour définir la validité de la connexion */
    protected void setConnectionUp(boolean isUp){
        this.connectionUp=isUp;
    }
}
