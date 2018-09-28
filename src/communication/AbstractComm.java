package communication;

public abstract class AbstractComm {
    public abstract void send(String string);
    protected abstract void listen();
    public void messageHandler(String message){
        System.out.println("IL FAUT OVVERIDE CETTE PUTAIN DE METHODE A L'INSTANCIATION: messangeHandler");
        //Override cette méthode quand on instancie la connexion
    }
}
