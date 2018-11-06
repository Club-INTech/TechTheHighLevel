package data.table.obstacle;

import utils.math.CircularRectangle;
import java.util.Locale;

/**
 * Définit un obstacle de forme rectangulaire avec angles arrondis
 * @see Obstacle
 *
 * @author rem
 */
public class CircularRectangularObstacle extends Obstacle {

    /**
     * Pour instancier un obstacle fixe
     * @see Obstacle
     */
    public CircularRectangularObstacle(CircularRectangle shape, boolean entryObstacleAllowed) {
        super(shape, 1200*1000, entryObstacleAllowed);
    }

    /**
     * Pour instancier un obstacle mobile
     * @see Obstacle
     */
    public CircularRectangularObstacle(CircularRectangle shape, int timeLife, boolean entryObstacleAllowed) {
        super(shape, timeLife, entryObstacleAllowed);
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return String.format(Locale.US, "Circular Rectangle Obstacle [%s, outDatedTime : %d, entryAllowed : %b]",
                this.shape.toString(), this.outDatedTime, this.entryObstacleAllowed);
    }
}
