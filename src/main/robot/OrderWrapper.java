package robot;

import pfg.config.Config;
import robot.OrdersEnums.*;
import robot.hooks.HookNames;
import utils.communication.Connections;
import utils.container.Service;
import utils.math.Vec2;

import java.util.Locale;

public class OrderWrapper implements Service {


    private Connections llConnection=Connections.TO_TEENSY;

    public void useActuator(Order order)
    {
        llConnection.send(order.getOrderStr());
    }

    public void moveLenghtwise(double distance){
        int d = (int)Math.round(distance);
        llConnection.send(MotionOrder.MOVE_LENTGHWISE,String.format(Locale.US,"%d", d));
    }

    public void turn(double angle)
    {
        llConnection.send(MotionOrder.TURN, String.format(Locale.US,"%.3f",angle));
    }

    public void immobilise(){
        llConnection.send(MotionOrder.STOP);
    }


    public void setTranslationnalSpeed(float speed)
    {
        llConnection.send(SpeedOrder.SET_TRANSLATION_SPEED);
    }


    public void setRotationnalSpeed(double rotationSpeed)
    {
        llConnection.send(SpeedOrder.SET_ROTATIONNAL_SPEED, String.format(Locale.US, "%.3f", (float)rotationSpeed));
    }

    public void setPositionAndOrientation(int x, int y, double orientation)
    {
        llConnection.send(PositionAndOrientationOrder.SET_POSITION , String.format("%d",x), String.format("%d",y), String.format("%.3f",orientation));
    }

    public void setOrientation(float orientation)
    {
        llConnection.send( PositionAndOrientationOrder.SET_ORIENTATION,String.format(Locale.US,"%.3f",orientation));
    }

    public void configureHook(int id, Vec2 posTrigger, int tolerency, double orientation, double tolerencyAngle, String order){
        llConnection.send(HooksOrder.INITIALISE_HOOK, String.format("%d", id), posTrigger.toStringEth(), String.format("%d", tolerency),String.format("%f",orientation), String.format("%f",tolerencyAngle),order);
    }

    /**
     * Active un hook
     * @param hook
     */
    public void enableHook(HookNames hook){
        llConnection.send( HooksOrder.ENABLE_HOOK, String.format("%d", hook.getId()));
    }

    /**
     * Desactive le hook
     * @param hook
     */
    public void disableHook(HookNames hook){
        llConnection.send(HooksOrder.DISABLE_HOOK, String.format("%d", hook.getId()));
    }


    @Override
    public void updateConfig(Config config) {

    }

    /**
     * On set la connection, c'est pour faire les tests en local, faire très attention quand on utilise cette méthode
     * @param connection : à qui on veut envoyer des ordres
     */
    public void setConnection(Connections connection) {
        this.llConnection = connection;
    }
}
