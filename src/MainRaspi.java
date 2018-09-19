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
	
        //RobotState.BRAS_AVANT_DEPLOYE.getData();
        System.out.println("Begin");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        teensy.send(Order.Ping, false);

        long a = System.currentTimeMillis();
        for (int i=0; i<100000; i++) {
            teensy.send(Order.CXYO, false, Integer.toString(i), "0", "0");
            teensy.send(Order.XYO, false);
        }
        long b=System.currentTimeMillis();


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("End: "+(b-a)+"ms");

        teensy.close();
    }
}
