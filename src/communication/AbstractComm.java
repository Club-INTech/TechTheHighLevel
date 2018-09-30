package communication;

public abstract class AbstractComm {

    private boolean connectionUp=true;

    public abstract void send(String string);
    public abstract String read() throws ConnectionException;
    public abstract boolean hasReceivedSomething();
    public boolean isConnectionUp(){
        return this.connectionUp;
    }
    public void setConnectionUp(boolean isUp){
        this.connectionUp=isUp;
    }
}
