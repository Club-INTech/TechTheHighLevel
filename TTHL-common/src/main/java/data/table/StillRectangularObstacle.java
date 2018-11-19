package data.table;

import utils.math.Rectangle;
import utils.math.Vec2;

/**
 * Classe implémentant les obstacles rectangulaires
 */
public class StillRectangularObstacle extends Obstacle {
    /**
     * Construit un obstacle rectangulaire
     * @param rectangle rectangle représentant l'obstacle
     */
    public StillRectangularObstacle(Rectangle rectangle) {
        super(rectangle);
    }

    /**
     * Construit un obstacle circulaire à partir d'un centre et des dimensions
     * @param center    centre du rectangle
     * @param length    longueur du rectangle
     * @param width     largeur du rectangle
     */
    public StillRectangularObstacle(Vec2 center, int length, int width) {
        super(new Rectangle((center), length, width));
    }

    /**
     * @see Obstacle#clone()
     */
    @Override
    public Obstacle clone() throws CloneNotSupportedException {
        return new StillRectangularObstacle((Rectangle) this.shape.clone());
    }

    /**
     * @see Obstacle#toString()
     */
    @Override
    public String toString() {
        return "Obstacle " + shape.toString();
    }
}
