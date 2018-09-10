import communication.AbstractComm;
import communication.Order;
import communication.TCPIPClient;
import communication.TCPIPServer;

public class Main {

    public static void main(String[] args) {

        Thread a = new Thread() {
            @Override
            public void run() {
                AbstractComm conA = new TCPIPServer(1337);
            }
        };
        a.start();
        AbstractComm conB = new TCPIPClient("localhost", 1337);
        conB.send(Order.GoTo, false);
        
    }
}
