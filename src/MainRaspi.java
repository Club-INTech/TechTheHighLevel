import communication.Order;
import communication.TCPIPClient;
import data.RobotState;

public class MainRaspi {
    public static void main(String[] args){
        TCPIPClient teensy = new TCPIPClient("192.168.0.1", 23500){
            @Override
            public void messageHandler(String message){
                System.out.println(message);
            }
        };

        RobotState.BRAS_AVANT_DEPLOYE.getData();
        System.out.println("Begin");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i=0; i<1000; i++) {
            teensy.send(Order.NewHook, false, new String[]{Integer.toString(i), "600", "2", "0.01", "?"});
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("End");

        teensy.close();
    }
}
