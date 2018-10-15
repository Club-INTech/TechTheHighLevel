package utils.communication;

/** Exception qui est levée lorsqu'une connexion a un problème */
public class ConnectionException extends Exception{

    private String message;

    /** Constructeur */
    public ConnectionException(String message){
        this.message=message;
    }

    /** Renvoie le message associé à l'exception*/
    public String getMessage(){
        return this.message;
    }
}
