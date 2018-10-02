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

/** Classe étalissant une connexion TCP IP avec une IP et un port spécifié
 * @author nayht
 */
public class TCPIPClient extends TCPIPAbstract{

    /** Fonction permettant de se connecter à l'IP choisie */
    public void connect(){
        //On définit le thread s'occupant de l'établissement de la connexion
        Thread waitingForConnectionThread = new Thread(() -> {
            try {
                socket = new Socket(); //On crée une nouvelle socket
                SocketAddress address = new InetSocketAddress(this.ip, this.port); //On crée l'objet contenant adresse à laquelle on veut se connecter

                //Tant que la connexion n'est pas établie
                while (!socket.isConnected()) {
                    try {
                        socket.connect(address); //On essaye de se connecter
                    } catch (IOException e) { //Si on n'a pas réussi à se connecter
                        try {
                            Thread.sleep(100); //On attend 100ms
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        socket = new Socket(); //On recrée la socket pour retenter une nouvelle connexion
                    }
                }
                //On définit les canaux d'entrée et de sortie
                listeningData = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                sendingData = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);

                //On marque la connexion comme active
                setConnectionUp(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //On tue le thread s'occupant de l'établissement de la connexion
            Thread.currentThread().interrupt();
        });

        //On lance le thread s'occupant de l'établissement de la connexion
        waitingForConnectionThread.start();
    }

    /** Constructeur */
    public TCPIPClient(String ip, int port){
        super(port);
        this.ip = ip; //On spécifie l'IP sur laquelle on se connecte
    }
}
