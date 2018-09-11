package junit;

import communication.AbstractComm;
import communication.Order;
import communication.TCPIPClient;

public class TCPIPCommunicationClient {

    public static void main(String[] ip) {
        if (ip.length>1) {
            AbstractComm conB = new TCPIPClient(ip[0], 1337);
            for (int i = 0; i < 100; i++) {
                if (i % 4 == 0) {
                    conB.send(Order.Test, false);
                } else if (i % 4 == 1) {
                    conB.send(Order.Test1, false);
                } else if (i % 4 == 2) {
                    conB.send(Order.Test2, false);
                } else if (i % 4 == 3) {
                    conB.send(Order.Test3, false);
                }
            }
        }
        else{
            System.out.println("L'argument doit être une unique chaîne de caractères représentant l'ip");
        }

    }
}
