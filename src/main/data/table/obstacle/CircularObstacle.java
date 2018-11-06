package data.table.obstacle;

import utils.math.Circle;
import java.util.Locale;

/**
 * Définit un obstacle circulaire
 * @see Obstacle
 *
 * @author sam
 */
public class CircularObstacle extends Obstacle {

    /**
     * Pour instancier un obstacle fixe
     * @see Obstacle
     */
    public CircularObstacle(Circle circle, boolean entryObstacleAllowed) {
        super(circle, -1, entryObstacleAllowed);
    }

    /**
     * Pour instancier un obstacle mobile
     * @see Obstacle
     */
    public CircularObstacle(Circle circle, int timeLife, boolean entryObstacleAllowed){
        super(circle, timeLife, entryObstacleAllowed);
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return String.format(Locale.US, "Obstacle Circular [%s, outDatedTime : %d, entryAllowed : %b]",
                this.shape.toString(), this.outDatedTime, this.entryObstacleAllowed);
    }
}
