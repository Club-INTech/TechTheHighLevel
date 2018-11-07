package utils.communication;

import java.io.IOException;
import java.net.Socket;

/**
 * Interface Socket client
 * @see SocketInterface
 *
 * @author william, rem
 */
public class SocketClientInterface extends SocketInterface {

    /**
     * Construit une interface de connexion point-à-point se connectant directement à un port ouvert
     * @param ipAdress  adresse ip
     * @param port      port auquel se connecter
     * @param readOnly  true si connexion à lire uniquement
     */
    public SocketClientInterface(String ipAdress, int port, boolean readOnly) {
        super(ipAdress, port, readOnly);
    }

    @Override
    public synchronized void init() throws CommunicationException {
        new Thread(() -> {
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start < CONNECTION_TIMEOUT) {
                try {
                    synchronized (this) {
                        this.socket = new Socket(ipAddress, port);
                        this.initBuffers();
                    }
                } catch (IOException | CommunicationException e) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
