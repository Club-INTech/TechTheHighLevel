package data.table;

import data.graphe.Ridge;
import utils.maths.Circle;
import utils.maths.Segment;
import utils.maths.Vector;

/**
 * Classe implémentant les obstacles mobiles circulaires, dans notre cas les autres robots (adverse & buddy)
 */
public class MobileCircularObstacle extends Obstacle
{
    /**
     * Temps de vie de l'obstacle (en millisecondes) : sert à retirer l'obstacle lorsqu'il n'est plus détecté
     */
    private long outDatedTime;

    /**
     * Temps de vie lors de la création
     * override par la config
     */
    private static int DEFAULT_LIFE_TIME    = 500;

    /**
     * Constructeur à partir d'une position et d'un rayon
     * @param position  position du centre du cercle représentant l'avdversaire
     * @param ray       rayon du cercle représentant l'adversaire
     */
    public MobileCircularObstacle(Vector position, int ray) {
        super(new Circle(position, ray));
        this.outDatedTime = DEFAULT_LIFE_TIME + System.currentTimeMillis();
    }

    /**
     * Constructeur à partir d'un cercle
     * @param circle    cercle représentant l'obstacle
     */
    public MobileCircularObstacle(Circle circle) {
        super(circle);
        this.outDatedTime = DEFAULT_LIFE_TIME + System.currentTimeMillis();
    }

    /**
     * Met à jour la position et le temps de vie de l'obstacle
     * @param newPosition   nouvelle position
     */
    public void update(Vector newPosition) {
        this.shape.setCenter(newPosition);
        this.outDatedTime = DEFAULT_LIFE_TIME + System.currentTimeMillis();
    }

    @Override
    public boolean isInObstacle(Vector point) {
        return this.shape.isInShape(point);
    }

    @Override
    public boolean intersect(Segment segment) {
        return this.shape.intersect(segment);
    }

    @Override
    public Obstacle clone() throws CloneNotSupportedException {
        return new MobileCircularObstacle((Circle) this.shape.clone());
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof MobileCircularObstacle) {
            return this.shape.equals(((MobileCircularObstacle) object).shape);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.shape.hashCode();
    }

    @Override
    public String toString() {
        return "Obstacle mobile circulaire " + this.shape.toString();
    }

    /**
     * Getters & Setters
     */
    public long getOutDatedTime() {
        return outDatedTime;
    }
    public void setLifeTime(int lifeTime) {
        this.outDatedTime = lifeTime;
    }

    /**
     * Utilisé pour les tests
     */
    public static int getDefaultLifeTime() {
        return DEFAULT_LIFE_TIME;
    }
}
