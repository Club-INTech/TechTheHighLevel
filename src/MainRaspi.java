import communication.CommunicationWrapper;
import communication.Connections;
import keyboard.KeyboardHandler;
import robot.Order;


public class MainRaspi {
    public static void main(String[] args){
        CommunicationWrapper commWrapper = new CommunicationWrapper(){
            @Override
            protected void startAllConnections() {
                startConnection(Connections.TEENSY);
            }
        };
        Connections.TEENSY.send(Order.Montlhery);
        KeyboardHandler keyboard = new KeyboardHandler();
        boolean porteAvantOuverte=false;
        boolean porteArriereOuverte=false;
        boolean pompeAllumee=false;

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
            else if (keyboard.isVPressed()){
                if (porteAvantOuverte) {
                    Connections.TEENSY.send(Order.FermePorteAvant);
                    porteAvantOuverte=false;
                }
                else{
                    Connections.TEENSY.send(Order.OuvrePorteAvant);
                    porteAvantOuverte=true;
                }
            }
            else if (keyboard.isWPressed()){

            }
            else if (keyboard.isXPressed()){

            }
            else if (keyboard.isPPressed()){

            }
            else if (keyboard.isSpacePressed()){
                if (porteArriereOuverte) {
                    Connections.TEENSY.send(Order.FermePorteArriere);
                    porteArriereOuverte=false;
                }
                else{
                    Connections.TEENSY.send(Order.OuvrePorteArriere);
                    porteArriereOuverte=true;
                }
            }
            else if (keyboard.isCPressed()){
                if (pompeAllumee) {
                    Connections.TEENSY.send(Order.DesactiveLaPompe);
                    pompeAllumee=false;
                }
                else{
                    Connections.TEENSY.send(Order.ActiveLaPompe);
                    pompeAllumee=true;
                }
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
