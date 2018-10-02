package communication;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/** Classe attendant une connexion TCP IP entrante sur un port précis
 * @author nayht
 */

public class TCPIPServer extends TCPIPAbstract{

    /** Fonction permettant d'accepter la première connexion venant sur le port spécifié */
    public void connect(){
        //On définit le thread s'occupant de l'établissement de la connexion
        Thread waitingForConnectionThread = new Thread(() -> {
            try
            {
                //On initialise le socket en mode serveur
                ServerSocket serverSocket = new ServerSocket(port);

                //Méthode bloquante
                Socket connectionSocket = serverSocket.accept();

                //On définit les canaux d'entrée et de sortie
                listeningData = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                sendingData = new PrintWriter(new BufferedWriter(new OutputStreamWriter(connectionSocket.getOutputStream())), true);

                //On marque la connexion comme active
                setConnectionUp(true);
            } catch (IOException e)
            {
                e.printStackTrace();
            }

            //On tue le thread s'occupant de l'établissement de la connexion
            Thread.currentThread().interrupt();
        });

        //On lance le thread s'occupant de l'établissement de la connexion
        waitingForConnectionThread.start();
    }

    /** Constructeur */
    public TCPIPServer(int port){
        super(port);
    }
}
