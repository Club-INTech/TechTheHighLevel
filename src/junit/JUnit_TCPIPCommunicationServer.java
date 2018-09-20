package junit;

import utils.communication.AbstractComm;
import utils.communication.TCPIPServer;

public class JUnit_TCPIPCommunicationServer {

    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                AbstractComm conA = new TCPIPServer(1337);
            }
        };
        thread.start();
    }
}
