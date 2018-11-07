package data.table;

import data.graphe.Ridge;
import utils.maths.Rectangle;
import utils.maths.Segment;
import utils.maths.Vector;

/**
 * Classe implémentant les obstacles rectangulaires
 */
public class FixedRectangularObstacle extends Obstacle
{
    /**
     * Construit un obstacle rectangulaire
     * @param rectangle rectangle représentant l'obstacle
     */
    public FixedRectangularObstacle(Rectangle rectangle) {
        super(rectangle);
    }

    /**
     * Construit un obstacle circulaire à partir d'un centre et des dimensions
     * @param center    centre du rectangle
     * @param length    longueur du rectangle
     * @param width     largeur du rectangle
     */
    public FixedRectangularObstacle(Vector center, int length, int width) {
        super(new Rectangle((center), length, width));
    }

    /**
     * @see Obstacle#isInObstacle(Vector)
     */
    @Override
    public boolean isInObstacle(Vector point) {
        return shape.isInShape(point);
    }

    /**
     * @see Obstacle#intersect(Segment)
     */
    @Override
    public boolean intersect(Segment segment) {
        return this.shape.intersect(segment);
    }

    /**
     * @see Obstacle#clone()
     */
    @Override
    public Obstacle clone() throws CloneNotSupportedException {
        return new FixedRectangularObstacle((Rectangle) this.shape.clone());
    }

    /**
     * @see Obstacle#equals(Object)
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof FixedRectangularObstacle) {
            return this.shape.equals(((FixedRectangularObstacle) object).shape);
        }
        return false;
    }

    /**
     * @see Obstacle#hashCode()
     */
    @Override
    public int hashCode() {
        return this.shape.hashCode();
    }

    /**
     * @see Obstacle#toString()
     */
    @Override
    public String toString() {
        return "Obstacle " + shape.toString();
    }
}
