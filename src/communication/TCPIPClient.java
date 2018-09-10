package communication;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.InetSocketAddress;

public class TCPIPClient extends TCPIPAbstract{

    private String ip;

    private void connectTo(){
        Socket connectionSocket;
        try {
            //On crée la socket
            SocketAddress address;

            connectionSocket = new Socket();
            address = new InetSocketAddress(this.ip, this.port);


            while (!connectionSocket.isConnected()) {
                try {
                    connectionSocket.connect(address);
                }
                catch(IOException e){
                    connectionSocket = new Socket();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }

            //On définit les canaux d'entrée et de sortie
            this.listeningData = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            this.sendingData = new PrintWriter(new BufferedWriter(new OutputStreamWriter(connectionSocket.getOutputStream())),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Constructeur */
    public TCPIPClient(String ip, int port){
        super(port);
        this.ip = ip; //On spécifie l'IP sur laquelle on se connecte
        connectTo(); //On gère la demande de connexion
        listen(); //On lance le listener
    }
}
