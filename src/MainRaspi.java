import communication.AbstractComm;
import communication.Order;
import communication.TCPIPClient;

public class MainRaspi {
    public static void main(String[] args){
        AbstractComm teensy = new TCPIPClient("192.168.0.1", 1337);

        System.out.println("Begin");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        teensy.send(Order.Recule, false);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        teensy.send(Order.Stop, false);
    }
}
