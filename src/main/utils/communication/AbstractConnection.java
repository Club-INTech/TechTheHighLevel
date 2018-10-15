package utils.communication;


/** Classe mère de toutes les interfaces de connexion
 * @author nayht
 */
public abstract class AbstractConnection {

    private boolean connectionUp=false; //Permet de savoir si la connexion est active

    /** Fonction permettant de lancer l'établissement de la connexion */
    protected abstract void connect();

    /** Permet d'envoyer un message */
    public abstract void send(String string);

    /** Permet de lire un message reçu */
    public abstract String read() throws ConnectionException;

    /** Permet de fermer la connexion */
    public abstract void close();

    /** Getter pour savoir si la connxion est active */
    protected boolean isConnectionUp(){
        return this.connectionUp;
    }

    /** Setter pour définir la validité de la connexion */
    protected void setConnectionUp(boolean isUp){
        this.connectionUp=isUp;
    }

}
