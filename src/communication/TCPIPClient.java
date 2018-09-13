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
    private Socket socket;

    private void connectTo(){
        try {
            //On crée la socket
            SocketAddress address;

            this.socket = new Socket();
            address = new InetSocketAddress(this.ip, this.port);


            while (!this.socket.isConnected()) {
                try {
                    this.socket.connect(address);
                }
                catch(IOException e){
                    this.socket = new Socket();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }

            //On définit les canaux d'entrée et de sortie
            this.listeningData = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.sendingData = new PrintWriter(new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream())),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close(){
        try {
            synchronized (synchronizedThread) {
                this.listeningThread.interrupt();
                this.socket.close();
            }
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
