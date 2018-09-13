import communication.Order;
import communication.TCPIPClient;

public class MainRaspi {
    public static void main(String[] args){
        TCPIPClient teensy = new TCPIPClient("192.168.0.1", 23500){
            @Override
            public void messageHandler(String message){
                System.out.println(message);
            }
        };

        System.out.println("Begin");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        teensy.send(Order.Ping, false);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("End");

        teensy.close();
    }
}
