package utils.communication;

import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 */
public class SocketServerInterface extends SocketInterface {

    /**
     * Timeout de connexion du server
     */
    public static final int CONNECTION_TIMEOUT  = 5000;

    /**
     * Construit une interface de connexion point-à-point attendant la connexion
     * @param ipAddress     ip à laquelle se connecté
     * @param port          port de connexion du serveur
     * @param readOnly      true si il ne doit que lire les données
     */
    public SocketServerInterface(String ipAddress, int port, boolean readOnly) {
        super(ipAddress, port, readOnly);
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
