package data.table;

import utils.math.Segment;
import utils.math.Shape;
import utils.math.Vec2;

/**
 * Classe abstraite définissant la représentation d'un obstacle dans le code.
 */
public abstract class Obstacle
{
    /**
     * Forme de l'obstacle
     */
    protected Shape shape;

    /**
     * Construit un obstacle
     */
    protected Obstacle(Shape shape)
    {
        this.shape = shape;
    }

    /**
     * Méthode utile pour les collision
     * @return  true si le point se trouve dans l'obstacle
     */
    public abstract boolean isInObstacle(Vec2 point);

    /**
     * Méthode servant à construire et mettre à jour le Graphe
     * @param segment   le segment à tester
     * @return  true si le segment intersecte l'obstacle
     */
    public abstract boolean intersect(Segment segment);

    /**
     * @see Object#clone()
     */
    @Override
    public abstract Obstacle clone() throws CloneNotSupportedException;

    /**
     * @see Object#equals(Object)
     */
    @Override
    public abstract boolean equals(Object object);

    /**
     * @see Object#hashCode()
     */
    @Override
    public abstract int hashCode();

    /**
     * @see Object#toString()
     */
    @Override
    public abstract String toString();

    /**
     * Getter
     */
    public Vec2 getPosition() {
        return shape.getCenter();
    }
    public Shape getShape() {
        return shape;
    }
}
