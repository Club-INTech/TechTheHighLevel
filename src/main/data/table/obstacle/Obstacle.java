package data.table.obstacle;

import utils.math.Segment;
import utils.math.Shape;
import utils.math.Vec2;

/**
 * Définit ce qu'est un obstacle
 *
 * @author rem, sam
 */
public abstract class Obstacle {

    /**
     * Forme de l'obstacle
     */
    protected Shape shape;

    /**
     * Durée de vie d'un obstacle. Un obstacle fixe à une durée de vie de 1200*10^3.
     */
    protected long outDatedTime;

    /**
     * Autorise ou non l'entrée du main.robot dans un obstacle
     */
    protected boolean entryObstacleAllowed;

    /**
     * Construit un obstacle
     * @param shape     forme de l'obstacle
     * @param timeLife  durée de vie de l'obstacle
     * @param entryObstacleAllowed
     *                  true si l'on peut entrer dans l'obstacle
     */
    public Obstacle(Shape shape, int timeLife, boolean entryObstacleAllowed) {
        this.shape = shape;
        this.entryObstacleAllowed = entryObstacleAllowed;
        this.outDatedTime = timeLife + System.currentTimeMillis();
    }

    /**
     * @param point     le point à tester
     * @return  true si le point est dans l'obstacle
     */
    public boolean isInObstacle(Vec2 point) {
        return this.shape.isInShape(point);
    }

    /**
     * @param segment   segment à tester
     * @return  true si intersection entre l'obstacle et le segment
     */
    public boolean intersect(Segment segment) {
        return this.shape.intersect(segment);
    }

    /**
     * Indique si deux obstacles sont égaux
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof Obstacle) {
            return this.shape.equals(((Obstacle) object).shape) &&
                    this.outDatedTime == ((Obstacle) object).outDatedTime &&
                    this.entryObstacleAllowed == ((Obstacle) object).entryObstacleAllowed;
        }
        return false;
    }

    /**
     * Génére un hashCode à partir de l'obstacle. Utile pour les hashMap
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        return this.shape.hashCode() + (int) this.outDatedTime*1000;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public abstract String toString();

    /**
     * Getter
     */
    public Shape getShape() {
        return shape;
    }
    public Vec2 getPosition() {
        return shape.getCenter();
    }
    public void setPosition(Vec2 newPosition) {
        this.shape.setCenter(newPosition);
    }
}
