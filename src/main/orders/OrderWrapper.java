package orders;

import connection.Connection;
import orders.order.ConfigOrder;
import orders.order.MotionOrder;
import pfg.config.Config;
import utils.ConfigData;
import utils.communication.CommunicationException;
import utils.container.Service;
import utils.maths.MathLib;
import utils.maths.Vector;

import java.util.Locale;

/**
 * Map des méthodes sur des ordres LL
 */
public class OrderWrapper implements Service {

    /**
     * Connection à laquelle parler
     */
    private Connection lowLevelConnection;

    /**
     * True si besoin de symetriser les ordres
     */
    private boolean symetrie;

    /**
     * Pour le container
     */
    private OrderWrapper() {}

    /**
     * Permet d'amorcer un mouvement en avant/arrière du robot
     * @param d     distance de mouvement
     */
    public void moveLengthwise(int d) {
        try {
            lowLevelConnection.send(String.format(Locale.US,
                    "%s %d", MotionOrder.MOVE_LENGTHWISE.getStringOrder(), d));
        } catch (CommunicationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet de tourner à l'angle indiqué
     * @param angle angle (en absolu)
     */
    public void turn(double angle) {
        if (symetrie) {
            angle = MathLib.modulo(Math.PI - angle, 2*Math.PI);
        }
        try {
            lowLevelConnection.send(String.format(Locale.US,
                    "%s %.3f", MotionOrder.TURN.getStringOrder(), angle));
        } catch (CommunicationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet d'envoyer l'ordre de déplacement vers un point de la table
     * @param point le point auquel se déplacer
     */
    public void moveToPoint(Vector point) {
        if (symetrie) {
            point.setX(-point.getX());
        }
        try {
            lowLevelConnection.send(String.format(Locale.US,
                    "%s %d %d", MotionOrder.MOVE_TO_POINT.getStringOrder(), point.getX(), point.getY()));
        } catch (CommunicationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Immobilise le robot
     */
    public void immobilise() {
        try {
            lowLevelConnection.send(MotionOrder.STOP.getStringOrder());
        } catch (CommunicationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Configure position et orientation du LL
     * @param position
     *              position
     * @param orientation
     *              orientation
     */
    public void setPositionAndOrientation(Vector position, double orientation) {
        if (symetrie) {
            position.setX(-position.getX());
            orientation = MathLib.modulo(Math.PI - orientation, 2*Math.PI);
        }
        try {
            lowLevelConnection.send(String.format(Locale.US,
                    "%s %d %d %.3f", ConfigOrder.SET_XYO.getStringOrder(), position.getX(), position.getY(), orientation));
        } catch (CommunicationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Configure la position du LL
     * @param position
     *              position
     */
    public void setPosition(Vector position) {
        if (symetrie) {
            position.setX(-position.getX());
        }
        try {
            lowLevelConnection.send(String.format(Locale.US,
                    "%s %d %d", ConfigOrder.SET_POSITION.getStringOrder(), position.getX(), position.getY()));
        } catch (CommunicationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Configure l'orientation du robot
     * @param orientation
     *              orientation
     */
    public void setOrientation(double orientation) {
        if (symetrie) {
            orientation = MathLib.modulo(Math.PI - orientation, 2*Math.PI);
        }
        try {
            lowLevelConnection.send(String.format(Locale.US,
                    "%s %.3f", ConfigOrder.SET_ORIENTATION.getStringOrder(), orientation));
        } catch (CommunicationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Configure la vitesse du LL
     * @param speed nouvelle vitesse
     */
    public void setSpeed(Speed speed) {
        try {
            lowLevelConnection.send(String.format(Locale.US,
                    "%s %d %.3f", ConfigOrder.SET_SPEED.getStringOrder(), speed.getTranslationSpeed(), speed.getRotationSpeed()));
        } catch (CommunicationException e) {
            e.printStackTrace();
        }
    }

    public void configureHook(Hook... hooks) {

    }

    public void disableHook(Hook... hooks) {

    }

    public void unableHook(Hook... hooks) {

    }

    /**
     * @see Service#updateConfig(Config)
     */
    @Override
    public void updateConfig(Config config) {
        boolean master = config.getBoolean(ConfigData.MASTER);
        if (master) {
            this.lowLevelConnection = Connection.TEENSY_MASTER;
        } else {
            this.lowLevelConnection = Connection.TEENSY_SLAVE;
        }
        this.symetrie = config.getString(ConfigData.COULEUR).equals("jaune");
    }

    /**
     * Set la connection à utiliser pour les envois d'ordres
     * ATTENTION : utilisé uniquement pour les tests
     * @param connection    la nouvelle connection
     */
    public void setLowLevelConnection(Connection connection) {
        this.lowLevelConnection = connection;
    }
}
