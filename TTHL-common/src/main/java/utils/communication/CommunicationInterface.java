package utils.communication;

import java.util.Optional;

/**
 * Définit les fonctionnalitées d'une interface de communication
 */
public interface CommunicationInterface {
    /**
     * Méthode pour envoyer un message
     * @param message   le corps du message
     * @throws CommunicationException
     *                  en cas de problèmes de communication
     */
    void send(String message) throws CommunicationException;

    /**
     * Méthode de lecture d'un message
     * @return  le message ou null si l'on a rien recu
     * @throws CommunicationException
     *                  en cas de problèmes de communication
     */
    Optional<String> read() throws CommunicationException;

    /**
     * Initialise la connection
     * @throws CommunicationException
     *                  en cas de problèmes de communication
     */
    void init() throws CommunicationException;

    /**
     * Ferme la connection
     * @throws CommunicationException
     *                  en cas de problèmes de communication
     */
    void close() throws CommunicationException;

    /**
     * @return true si l'on peut communiquer avec cette interface
     */
    boolean isInterfaceOpen();
}
