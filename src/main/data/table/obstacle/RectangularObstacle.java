package data.table.obstacle;

import utils.math.Rectangle;
import java.util.Locale;

/**
 * Définit un obstacle rectangulaire
 * @see Obstacle
 *
 * @author sam
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
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return String.format(Locale.US, "Obstacle Rectangulaire [%s, outDatedTime : %d, entryAllowed : %b]",
                this.shape.toString(), this.outDatedTime, this.entryObstacleAllowed);
    }
}
