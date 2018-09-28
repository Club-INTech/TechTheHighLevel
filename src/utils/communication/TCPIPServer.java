package utils.communication;

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
        ServerSocket serverSocket;
        try {
            //On initialise le socket en mode serveur
            serverSocket = new ServerSocket(this.port);

            //Méthode bloquante
            this.socket = serverSocket.accept();

            //On définit les canaux d'entrée et de sortie
            this.listeningData = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.sendingData = new PrintWriter(new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream())),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Constructeur */
    public TCPIPServer(int port){
        super(port);
        acceptConnection(); //On gère l'acceptation de la première connexion
        listen(); //On lance le listener
    }
}
