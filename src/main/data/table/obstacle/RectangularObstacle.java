package main.data.table.obstacle;

import main.utils.math.Rectangle;
import main.utils.math.Segment;
import main.utils.math.Vec2;

public class RectangularObstacle extends Obstacle {

    /**
     *      Conventions :
     *
     *      y
     *      ^
     *      |---------------------------------
     *      |                                |
     *      |                                |
     *      |               0                |
     *      |                                |
     *      |                                |
     *      |----------------------------------> x
     *
     *      0: origine du rectangle, indiqué dans le Vec2 position d'Obstacle
     *           *
     */

    private Rectangle rectangle;


    /**
     * Pour instancier un obstacle fixe
     *
     *
     * @param entryObstacleAllowed  autoriser ou non le main.robot à rentrer dans obstacle
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
     * @param entryObstacleAllowed  autoriser ou non le main.robot à rentrer dans obstacle
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
        return rectangle.intersectsWithSegment(segment);
    }

    @Override
    public boolean equals(Object Obj) {
        if (Obj instanceof RectangularObstacle){
            RectangularObstacle rectangularObstacle = (RectangularObstacle) Obj;
            return this.getPosition().equals(rectangularObstacle.getPosition()) && this.rectangle.equals(rectangularObstacle.getRectangle());
        }
        return false;
    }

    public Vec2 getPosition(){ return super.position;   }

    public Rectangle getRectangle() { return rectangle; }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return null;
    }
}
