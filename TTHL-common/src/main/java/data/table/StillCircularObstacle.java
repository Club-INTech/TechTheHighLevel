package data.table;

import utils.math.Circle;
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

    @Override
    public Obstacle clone() throws CloneNotSupportedException {
        return new StillCircularObstacle((Circle) this.shape.clone());
    }

    @Override
    public String toString() {
        return "Obstacle fixe circulaire " + shape.toString();
    }
}
