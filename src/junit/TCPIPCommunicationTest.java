package junit;

import communication.AbstractComm;
import communication.Order;
import communication.TCPIPClient;
import communication.TCPIPServer;

public class TCPIPCommunicationTest {

    public static void main(String[] ip) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                AbstractComm conA = new TCPIPServer(1337);
            }
        };
        thread.start();
        AbstractComm conB = new TCPIPClient("localhost", 1337);
        for (int i=0; i<100; i++) {
            if (i%4==0) {
                conB.send(Order.Test, false);
            }
            else if (i%4==1){
                conB.send(Order.Test1, false);
            }
            else if (i%4==2){
                conB.send(Order.Test2, false);
            }
            else if (i%4==3){
                conB.send(Order.Test3, false);
            }
        }

    }
}
