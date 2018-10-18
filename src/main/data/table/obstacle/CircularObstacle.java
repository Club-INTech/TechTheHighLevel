package main.data.table.obstacle;

import main.utils.math.Circle;
import main.utils.math.Segment;
import main.utils.math.Vec2;

public class CircularObstacle extends Obstacle {

    /** Cercle définissant l'obstacle */
    private Circle circle;



    /** Pour instancier un obstacle fixe
     *
     * @param circle
     * @param entryObstacleAllowed autoriser ou non le main.robot à rentrer dans obstacle
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
     * @param entryObstacleAllowed  autoriser ou non le main.robot à rentrer dans obstacle
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
    public boolean equals(Object obj) {
        if (obj instanceof CircularObstacle){
            CircularObstacle circularObstacle = (CircularObstacle) obj;
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
