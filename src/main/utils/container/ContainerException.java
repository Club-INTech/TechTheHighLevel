package utils.container;

/**
 * Exception levé par le container en cas de dépendance circulaire ou de dépendances mal gérée entre les classes
 *
 * @author pf
 */
public class ContainerException extends Exception
{
    public ContainerException() {super();}
    public ContainerException(String message) {super(message);}
}
