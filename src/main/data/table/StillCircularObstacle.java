package data.table;

import utils.math.Circle;
import utils.math.Segment;
import utils.math.Vec2;

/**
 * Classe représentant les obstacles circulaires
 */
public class StillCircularObstacle extends Obstacle {
    /**
     * Constructeur position & rayon
     *
     * @param position  centre du cercle
     * @param ray   rayon du cercle
     */
    public StillCircularObstacle(Vec2 position, int ray) {
        super(new Circle(position, ray));
    }

    /**
     * Constructeur cercle
     * @param circle    cercle
     */
    public StillCircularObstacle(Circle circle) {
        super(circle);
    }

    /**
     * @see Obstacle#isInObstacle(Vec2)
     */
    @Override
    public boolean isInObstacle(Vec2 point) {
        return (point.minusVector(getPosition()).getR() < ((Circle) shape).getRadius());
    }

    /**
     * @see Obstacle#intersect(Segment)
     */
    @Override
    public boolean intersect(Segment segment) {
        return shape.intersect(segment);
    }

    @Override
    public Obstacle clone() throws CloneNotSupportedException {
        return new StillCircularObstacle((Circle) this.shape.clone());
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof StillCircularObstacle) {
            return this.shape.equals(((StillCircularObstacle) object).shape);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.shape.hashCode();
    }

    @Override
    public String toString() {
        return "Obstacle fixe circulaire " + shape.toString();
    }
}
