package utils.communication;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Définit l'interface de connexion utilisant une socket serveur
 * @see SocketInterface
 *
 * @author william, rem
 */
public class SocketServerInterface extends SocketInterface {

    /**
     * Construit une interface de connexion point-à-point attendant la connexion
     * @param ipAddress     ip à laquelle se connecté
     * @param port          port de connexion du serveur
     */
    public SocketServerInterface(String ipAddress, int port) {
        super(ipAddress, port);
    }

    @Override
    public void init() throws CommunicationException {
        new Thread(() -> {
            try {
                synchronized (this) {
                    ServerSocket serverSocket = new ServerSocket(port);
                    serverSocket.setSoTimeout(CONNECTION_TIMEOUT);
                    this.socket = serverSocket.accept();
                    this.initBuffers();
                }
            } catch (IOException | CommunicationException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
