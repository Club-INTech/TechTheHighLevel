package robot;

import pfg.config.Config;
import robot.OrdersEnums.*;
import robot.hooks.HookNames;
import utils.ConfigData;
import utils.Log;
import utils.communication.Connections;
import utils.container.Service;
import utils.math.Vec2;

import java.util.Locale;

/**
 * Classe qui permet d'envoyer tous les ordres
 */
public class OrderWrapper implements Service {

    /**
     * Symétrie
     */
    private boolean symetry;

    /**On utitise comme connexion par défaut le bas niveau*/
    private Connections llConnection=Connections.TO_TEENSY;

    private SymmetrizedActuatorOrderMap symmetrizedActuatorOrderMap;

    /**
     * Conctructeur en privé car déjà instancié par le container, pour éviter que qqn l'instancie
     */
    private OrderWrapper(SymmetrizedActuatorOrderMap symmetrizedActuatorOrderMap){
        this.symmetrizedActuatorOrderMap=symmetrizedActuatorOrderMap;

    }

    /**
     * Permet d'envoyer un ordre au bas niveau
     * @param order ordre quelconque
     */
    public void useActuator(Order order)
    {
        Order symetrisedOrder;
        if(symetry && order instanceof ActionsOrder){
            symetrisedOrder=this.symmetrizedActuatorOrderMap.getSymmetrizedActuatorOrder((ActionsOrder) order);
            if(symetrisedOrder != null){
                order=symetrisedOrder;
            }
        }
        llConnection.send(order.getOrderStr());
    }

    /**
     * On envoit au bas niveau comme ordre d'avancer d'une certaine distance
     * @param distance distance dont on avance
     */
    public void moveLenghtwise(double distance){
        int d = (int)Math.round(distance);
        llConnection.send(MotionOrder.MOVE_LENTGHWISE,String.format(Locale.US,"%d", d));
    }

    /**
     * On envoit au bas niveau comme ordre de tourner
     * @param angle  angle aveclequel on veut tourner
     */
    public void turn(double angle)
    {
        if(symetry){
            angle=(Math.PI - angle)%(2*Math.PI);
        }
        llConnection.send(MotionOrder.TURN, String.format(Locale.US,"%.3f",angle));
    }

    /**
     * On envoit au bas niveau comme ordre de s'arrêter
     */
    public void immobilise(){
        llConnection.send(MotionOrder.STOP);
    }

    /**
     * On dit au bas niveau la vitesse de translation qu'on veut
     * @param speed la vitesse qu'on veut
     */
    public void setTranslationnalSpeed(float speed)
    {
        llConnection.send(SpeedOrder.SET_TRANSLATION_SPEED,String.format(Locale.US,"%.3f",speed));
    }

    /**
     * On dit au abs niveau la vitesse de rotation qu'on veut
     * @param rotationSpeed la vitesse de rotation qu'on veut
     */
    public void setRotationnalSpeed(double rotationSpeed)
    {
        llConnection.send(SpeedOrder.SET_ROTATIONNAL_SPEED, String.format(Locale.US, "%.3f", (float)rotationSpeed));
    }

    /**
     * Modifie les vitesses de translation et de rotation du robot
     * @param speed enum qui contient les deux vitesses
     */
    public void setBothSpeed(Speed speed){
        this.setTranslationnalSpeed(speed.getTranslationSpeed());
        this.setRotationnalSpeed(speed.getRotationSpeed());
    }

    /**
     * On dit au bas niveau dans quelle position on est et quelle orientation on adopte
     * @param pos position du robot
     * @param orientation orientation du robot
     */
    public void setPositionAndOrientation(Vec2 pos, double orientation)
    {
        int x=pos.getX();
        int y=pos.getY();
        if(symetry){
            x=-x;
            orientation=(Math.PI - orientation)%(2*Math.PI);
        }
        llConnection.send(PositionAndOrientationOrder.SET_POSITION_AND_ORIENTATION , String.format("%d",x), String.format("%d",y), String.format(Locale.US,"%.3f",orientation));
    }

    /**
     * On dit au bas niveau quelle orientation le robot a
     * @param orientation orientation du robot
     */
    public void setOrientation(double orientation)
    {
        if(symetry){
            orientation=(Math.PI - orientation)%(2*Math.PI);
        }
        llConnection.send( PositionAndOrientationOrder.SET_ORIENTATION,String.format(Locale.US,"%.3f",orientation));
    }

    /**
     * Permet de configurer un hook
     * @param id id du hook
     * @param posTrigger position où on active le hook
     * @param tolerency tolérance qu'on veut sur la position
     * @param orientation l'orientation du robot où on active le hook
     * @param tolerencyAngle l'angle de tolérance sur l'orientation
     * @param order l'ordre à exécuter pendant que le robot bouge
     */
    public void configureHook(int id, Vec2 posTrigger, int tolerency, double orientation, double tolerencyAngle, Order order){
        Order symetrisedOrder;
        if(symetry){
            posTrigger=posTrigger.symetrize();
            Log.HOOK.debug("la position envoyée au bas niveau pour le hook"+posTrigger.toString());
            orientation=(Math.PI - orientation)%(2*Math.PI);
            Log.HOOK.debug("l'orientation envoyée au bas niveau pour le hook"+orientation);
            if( order instanceof ActionsOrder) {
                symetrisedOrder = symmetrizedActuatorOrderMap.getSymmetrizedActuatorOrder((ActionsOrder) order);
                if(symetrisedOrder !=null){
                    order=symetrisedOrder;
                }
            }

        }
        llConnection.send(HooksOrder.INITIALISE_HOOK, String.format("%d", id), posTrigger.toStringEth(), String.format("%d", tolerency),String.format(Locale.US,"%.3f",orientation), String.format(Locale.US,"%.3f",tolerencyAngle),order.getOrderStr());
    }

    /**
     * Active un hook
     * @param hook hook à activer
     */
    public void enableHook(HookNames hook){
        llConnection.send( HooksOrder.ENABLE_HOOK, String.format("%d", hook.getId()));
    }

    /**
     * Desactive un hook
     * @param hook
     */
    public void disableHook(HookNames hook){
        llConnection.send(HooksOrder.DISABLE_HOOK, String.format("%d", hook.getId()));
    }


    @Override
    public void updateConfig(Config config) {
        //On est du côté violet par défaut , le HL pense en violet
        symetry=config.getString(ConfigData.COULEUR).equals("jaune");
    }

    /**
     * On set la connection, c'est pour faire les tests en local, faire très attention quand on utilise cette méthode
     * @param connection : à qui on veut envoyer des ordres
     */
    public void setConnection(Connections connection) {
        this.llConnection = connection;
    }


}
