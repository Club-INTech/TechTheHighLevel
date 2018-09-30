package communication;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPIPServer extends TCPIPAbstract{

    /** Fonction permettant d'accepter la première connexion venant sur le port spécifié */
    private void acceptConnection(){
        Thread waitingForConnection = new Thread(() -> {
            ServerSocket serverSocket;
            Socket connectionSocket;
            try
            {
                //On initialise le socket en mode serveur
                serverSocket = new ServerSocket(port);

                //Méthode bloquante
                connectionSocket = serverSocket.accept();

                //On définit les canaux d'entrée et de sortie
                listeningData = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                sendingData = new PrintWriter(new BufferedWriter(new OutputStreamWriter(connectionSocket.getOutputStream())), true);
                setConnectionUp(true);
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            Thread.currentThread().interrupt();
        });
        waitingForConnection.start();
    }

    /** Constructeur */
    public TCPIPServer(int port){
        super(port);
        acceptConnection(); //On gère l'acceptation de la première connexion
    }
}
