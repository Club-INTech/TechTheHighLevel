package utils.communication;

/**
 * Exception levée en cas de problème de communication
 *
 * @author rem
 */
public class CommunicationException extends Exception {
    public CommunicationException() {super();}
    public CommunicationException(String message) {super(message);}
}
