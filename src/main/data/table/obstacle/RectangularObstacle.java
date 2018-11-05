package data.table.obstacle;

import utils.math.Rectangle;

/**
 * Définit un obstacle rectangulaire
 * @see Obstacle
 */
public class RectangularObstacle extends Obstacle {

    /**
     * Pour instancier un obstacle fixe
     * @see Obstacle
     */
    public RectangularObstacle(Rectangle rectangle, boolean entryObstacleAllowed){
        super(rectangle, -1, entryObstacleAllowed);
    }

    /**
     * Pour instancier un obstacle mobile
     * @see Obstacle
     */
    public RectangularObstacle(Rectangle rectangle, int timeLife, boolean entryObstacleAllowed){
        super(rectangle, timeLife, entryObstacleAllowed);
    }

    /**
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RectangularObstacle){
            return this.shape.equals(((RectangularObstacle) obj).shape);
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
