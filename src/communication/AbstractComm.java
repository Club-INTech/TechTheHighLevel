package communication;

public abstract class AbstractComm {
    public abstract void send(Order order, boolean waitForCompletion, String... parameters);
    protected abstract void listen();
}
