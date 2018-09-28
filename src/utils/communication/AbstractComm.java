package utils.communication;

public abstract class AbstractComm {
    public abstract void send(String orderStr, String... parameters);
    protected abstract void listen();
    public void messageHandler(String message){
        //Override cette méthode quand on instancie la connexion
        System.out.println("IL FAUT OVERRIDE CETTE PUTAIN DE METHODE : messageHandler");
    }
}
