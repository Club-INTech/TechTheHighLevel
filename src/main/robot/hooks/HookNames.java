

 package robot.hooks;

import robot.OrdersEnums.Order;

import robot.OrdersEnums.Speed;
import robot.OrdersEnums.SpeedOrder;
import utils.math.Vec2;
import utils.math.VectCartesian;

 /**
 * Contient le nom des hooks et leurs paramètres associés
 * ATTENTION à ne pas mettre deux hooks avec le meme id !!
 */
public enum HookNames {

    // Example :
    SPEED_DOWN(1, new VectCartesian(50, 50), 5 ,0,Math.PI, Speed.SLOW_ALL),


    ;

    /** Ordre du hook */
    private Order order;

    /** Position de trigger du hook */
    private Vec2 position;

    /** Tolérence sur la position */
    private int tolerency; //en mm

    /** Id du hook, utile pour pouvoir l'activer/désactivé manuellement*/
    private int id;

    private double orientation;
    private double tolerencyAngle; //en radians

    /** Constructeur */
    HookNames(int id, Vec2 position, int tolerency, double orientation, double tolerencyAngle, Order order){
        this.id = id;
        this.position = position;
        this.tolerency = tolerency;
        this.order = order;
        this.orientation=orientation;
        this.tolerencyAngle=tolerencyAngle;
    }

    /** Getters & Setters */
    public Order getOrder() {
        return order;
    }
    public Vec2 getPosition() {
        return position;
    }
    public int getTolerency(){
        return tolerency;
    }
    public int getId() {
        return id;
    }

    public double getOrientation() {
        return orientation;
    }

    public double getTolerencyAngle() {
        return tolerencyAngle;
    }

    public void setPosition(Vec2 position) {
        this.position = position;
    }

}


