package utils.communication;

import java.io.IOException;
import java.net.Socket;

/**
 * Interface Socket client
 * @see SocketInterface
 *
 * @author rem
 */
public class SocketClientInterface extends SocketInterface {

    /**
     * Construit une interface de connexion point-à-point se connectant directement à un port ouvert
     * @param ipAdress  adresse ip
     * @param port      port auquel se connecter
     */
    public SocketClientInterface(String ipAdress, int port) {
        super(ipAdress, port);
    }

    @Override
    public synchronized void init() throws CommunicationException {
        new Thread(() -> {
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start < SocketInterface.CONNECTION_TIMEOUT) {
                synchronized (this) {
                    try {
                        this.socket = new Socket(ipAddress, port);
                        this.initBuffers();
                        break;
                    } catch (IOException | CommunicationException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
