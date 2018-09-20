package utils.communication;

public abstract class AbstractComm {
    public abstract void send(Order order, boolean waitForCompletion, String... parameters);
    protected abstract void listen();
    public void messageHandler(String message){
        //Override cette méthode quand on instancie la connexion
    }
}
