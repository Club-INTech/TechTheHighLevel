package data;

import utils.math.Vec2;
import utils.math.VectCartesian;

/**
 * Classe représentant les positions des robots (doublons)
 */
public class XYO {
    /**
     * Instance du robot
     */
    private static XYO robotXYO = null;

    /**
     * Instance du buddy
     */
    private static XYO buddyXYO = null;

    /**
     * Position
     */
    private Vec2 position;

    /**
     * Orientation
     */
    private double orientation;

    /**
     * Constructeur
     */
    private XYO(Vec2 position, double orientation) {
        this.position = position;
        this.orientation = orientation;
    }

    /**
     * Mise à jour des XYO
     * package-private
     */
    void update(String message) {
        String data[] = message.split(" ");
        this.position.setXY(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
        this.orientation = Double.parseDouble(data[2]);
    }

    /**
     * Getters
     */
    public static XYO getRobotInstance() {
        if (robotXYO == null) {
            // TODO : Remplir avec l'entryPosition !
        }
        return robotXYO;
    }
    public static XYO getBuddyInstance() {
        // TODO : Décider comment ca fonctionne ! et virer l'instanciation qui sert pour les tests
        if (buddyXYO == null) {
            buddyXYO = new XYO(new VectCartesian(0, 0), 0);
        }
        return buddyXYO;
    }
    public Vec2 getPosition() {
        return position;
    }
    public double getOrientation() {
        return orientation;
    }
}
