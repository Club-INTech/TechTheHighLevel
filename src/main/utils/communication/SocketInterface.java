package utils.communication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;

import java.net.Socket;
import java.util.Optional;

/**
 * Interface Socket client
 * @see CommunicationInterface
 *
 * @author rem
 */
public abstract class SocketInterface implements CommunicationInterface {
    /**
     * Adresse ip à laquelle se connecter
     */
    protected String ipAddress;

    /**
     * Port auquel se connecter
     */
    protected int port;

    /**
     * Socket
     */
    protected Socket socket;

    /**
     * Buffer de lecture
     */
    private BufferedReader input;

    /**
     * Buffer d'écriture
     */
    private BufferedWriter output;

    /**
     * Booléen décrivant si la socket est read only ou non
     */
    private boolean readOnly;

    /**
     * True si la connexion a été initialisée
     */
    private boolean initiate;

    /**
     * Construit une interface de connexion socket
     * @param ipAddress     l'addresse ip sur laquelle se connecter
     * @param port          port de connexion
     * @param readOnly      true si l'interface ne doit faire qu'écouter
     */
    public SocketInterface(String ipAddress, int port, boolean readOnly) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.readOnly = readOnly;
        this.initiate = false;
    }

    @Override
    public synchronized void send(String message) throws CommunicationException {
        if (readOnly || !initiate) {
            throw new CommunicationException("Envoie du message " + message + " impossible : connexion read-only");
        }
        try {
            this.output.write(message);
            this.output.newLine();
            this.output.flush();
        } catch (IOException e) {
            throw new CommunicationException("Envoie du message " + message + " impossible");
        }
    }

    @Override
    public synchronized Optional<String> read() throws CommunicationException {
        Optional<String> message = Optional.empty();
        try {
            if (this.input.ready() && initiate) {
                message = Optional.of(input.readLine());
            }
        } catch (IOException e) {
            throw new CommunicationException("Lecture du message impossible : la connexion est fermée");
        }
        return message;
    }

    @Override
    public synchronized void close() throws CommunicationException {
        try {
            if (this.initiate) {
                this.input.close();
                this.output.close();
                this.socket.close();
                this.initiate = false;
            }
        } catch (IOException e) {
            throw new CommunicationException("Impossible de fermer la communication");
        }
    }

    @Override
    public boolean isInterfaceOpen() {
        return initiate;
    }

    /**
     * Initialise les buffers IO
     * @throws CommunicationException
     *                  en cas de problème d'initialisation des buffers
     */
    protected void initBuffers() throws CommunicationException {
        try {
            this.input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.output = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            this.initiate = true;
        } catch (IOException e) {
            throw new CommunicationException("Impossible de créer les buffers IO");
        }
    }

    /**
     * Getter & Setter
     */
    public boolean isInitiate() {
        return initiate;
    }
}
