package data.table.obstacle;

import utils.math.Circle;
import utils.math.Segment;
import utils.math.Vec2;

/**
 * Définit un obstacle circulaire
 * @see Obstacle
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
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CircularObstacle){
            return this.shape.equals(((CircularObstacle) obj).shape) &&
                    entryObstacleAllowed == ((CircularObstacle) obj).entryObstacleAllowed;
        }
        return false;
    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        return 0;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return null;
    }
}
