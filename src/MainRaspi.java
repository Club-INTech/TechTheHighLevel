import communication.CommunicationWrapper;
import communication.Connections;
import keyboard.KeyboardHandler;
import robot.Order;


public class MainRaspi {
    public static void main(String[] args){
        KeyboardHandler keyboard = new KeyboardHandler();
        CommunicationWrapper commWrapper = new CommunicationWrapper(){
            @Override
            protected void startAllConnections() {
                startConnection(Connections.TEENSY);
            }
        };
        Connections.TEENSY.send(Order.Montlhery);

        while (true){
            if (keyboard.isUpPressed()){
                Connections.TEENSY.send(Order.Avance);
            }
            else if (keyboard.isDownPressed()){
                Connections.TEENSY.send(Order.Recule);
            }
            else if (keyboard.isLeftPressed()){
                Connections.TEENSY.send(Order.Left);
            }
            else if (keyboard.isRightPressed()){
                Connections.TEENSY.send(Order.Right);
            }
            else{
                Connections.TEENSY.send(Order.Stop);
            }
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
