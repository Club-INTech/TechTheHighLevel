package data.table;

import data.graphe.Ridge;
import utils.maths.Circle;
import utils.maths.Segment;
import utils.maths.Vector;

/**
 * Classe représentant les obstacles circulaires
 */
public class FixedCircularObstacle extends Obstacle
{
    /**
     * Constructeur position & rayon
     *
     * @param position  centre du cercle
     * @param ray   rayon du cercle
     */
    public FixedCircularObstacle(Vector position, int ray) {
        super(new Circle(position, ray));
    }

    /**
     * Constructeur cercle
     * @param circle    cercle
     */
    public FixedCircularObstacle(Circle circle) {
        super(circle);
    }

    /**
     * @see Obstacle#isInObstacle(Vector)
     */
    @Override
    public boolean isInObstacle(Vector point) {
        return (point.withdrawNewVector(getPosition()).getRay() < ((Circle) shape).getRay());
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
        return new FixedCircularObstacle((Circle) this.shape.clone());
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof FixedCircularObstacle) {
            return this.shape.equals(((FixedCircularObstacle) object).shape);
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
