package data.table.obstacle;

import utils.math.Circle;
import utils.math.Segment;
import utils.math.Vec2;

public class CircularObstacle extends Obstacle {

    /** Cercle définissant l'obstacle */
    private Circle circle;



    /** Pour instancier un obstacle fixe
     *
     * @param circle
     * @param entryObstacleAllowed autoriser ou non le robot à rentrer dans obstacle
     */

    public CircularObstacle(Circle circle, boolean entryObstacleAllowed){
        super.position = circle.getCenter();
        this.circle = circle;
        this.timeLife = -1;
        this.entryObstacleAllowed = entryObstacleAllowed;
    }

    /** Pour instancier un obstacle mobile
     *
     * @param circle
     * @param timeLife              durée de vie de l'obstacle
     * @param entryObstacleAllowed  autoriser ou non le robot à rentrer dans obstacle
     */

    public CircularObstacle(Circle circle, int timeLife, boolean entryObstacleAllowed){
        super.position = circle.getCenter();
        this.circle = circle;
        this.timeLife = timeLife;
        this.entryObstacleAllowed = entryObstacleAllowed;
    }

    @Override
    public boolean isInObstacle(Vec2 point) {
        return this.circle.containsPoint(point);
    }

    @Override
    public boolean intersect(Segment segment) {
        return this.circle.intersectsWithSegment(segment);
    }

    @Override
    public boolean equals(Obstacle obstacle) {
        if (obstacle instanceof CircularObstacle){
            CircularObstacle circularObstacle = (CircularObstacle) obstacle;
            return circularObstacle.circle.getRadius() == this.circle.getRadius() && circularObstacle.getPosition().equals(this.getPosition());
        }
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

    public Vec2 getPosition() {
        return super.position;
    }

    public Circle getCircle() {
        return circle;
    }
}
