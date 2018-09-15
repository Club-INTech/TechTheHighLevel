import communication.Order;
import communication.TCPIPClient;
import config.ConfigInstance;
import data.RobotState;

public class MainRaspi {
    public static void main(String[] args){
        TCPIPClient teensy = new TCPIPClient("192.168.0.1", 23500){
            @Override
            public void messageHandler(String message){
                System.out.println(message);
            }
        };

        ConfigInstance a = new ConfigInstance();

        //RobotState.BRAS_AVANT_DEPLOYE.getData();
        System.out.println("Begin");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        teensy.send(Order.Ping, false);

        for (int i=0; i<1000; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            teensy.send(Order.CXYO, false, Integer.toString(i), "1000", "0");
        }

        teensy.send(Order.XYO, false);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("End");

        teensy.close();
    }
}
