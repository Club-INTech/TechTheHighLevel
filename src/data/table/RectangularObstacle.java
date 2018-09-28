package data.table;

import utils.math.Rectangle;
import utils.math.Segment;
import utils.math.Vec2;

public class RectangularObstacle extends Obstacle {

    /**
     *      Conventions :
     *
     *      y
     *      ^
     *      |---------------------------------
     *      |                                |
     *      |                                |
     *      |                                |
     *      |                                |
     *      |                                |
     *      O----------------------------------> x
     *
     *      0: origine du rectangle, indiqué dans le Vec2 position d'Obstacle
     *           *
     */

    private Rectangle rectangle;


    /**
     * Pour instancier un obstacle fixe
     *
     *
     * @param entryObstacleAllowed  autoriser ou non le robot à rentrer dans obstacle
     */

    public RectangularObstacle(Rectangle rectangle, boolean entryObstacleAllowed){
        super.position = rectangle.getCenter();
        this.rectangle = rectangle;
        this.timeLife = -1;
        this.entryObstacleAllowed = entryObstacleAllowed;
    }

    /**
     * Pour instancier un obstacle mobile
     *
     * @param timeLife              durée de vie de l'obstacle
     * @param entryObstacleAllowed  autoriser ou non le robot à rentrer dans obstacle
     */

    public RectangularObstacle(Rectangle rectangle, int timeLife, boolean entryObstacleAllowed){
        super.position = rectangle.getCenter();
        this.rectangle = rectangle;
        this.timeLife = timeLife;
        this.entryObstacleAllowed = entryObstacleAllowed;
    }

    @Override
    public boolean isInObstacle(Vec2 point) { return rectangle.containsPoint(point);   }

    @Override
    public boolean intersect(Segment segment) {
        return false;
    }

    @Override
    public boolean equals(Obstacle obstacle) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return null;
    }
}
