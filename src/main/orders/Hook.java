package orders;

import orders.order.ConfigOrder;
import orders.order.Order;
import utils.maths.Vector;

import java.util.Optional;

/**
 * Définit les hooks
 *
 * @author rem
 */
public enum Hook {
    SPEED_DOWN(new Vector(300, 420), Math.PI/2, 50, 0.6, ConfigOrder.SET_SPEED, Speed.ADAGIO),
    ;

    /**
     * Position d'activation du hook
     */
    private Vector position;

    /**
     * Orientation d'activation du hook
     */
    private double orientation;

    /**
     * Tolérance en position
     */
    private int posTolenrency;

    /**
     * Tolérance en angle
     */
    private double orientationTolerency;

    /**
     * Ordre à executer
     */
    private Order order;

    /**
     * Arguments optionnels
     */
    private Optional<Enum> arguments;

    Hook(Vector position, double orientation, int posTolenrency, double orientationTolerency, Order order, Speed arguments) {
        this.position = position;
        this.orientation = orientation;
        this.posTolenrency = posTolenrency;
        this.orientationTolerency = orientationTolerency;
        this.order = order;
        this.arguments = Optional.ofNullable(arguments);
    }
}
