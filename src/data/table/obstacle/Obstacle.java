package data.table.obstacle;

import utils.math.Segment;
import utils.math.Vec2;

public abstract class   Obstacle {

    /** Vecteur de position localisant un obstacle */
    protected Vec2 position;

    /** Durée de vie d'un obstacle. Un obstacle fixe à une durée de vie de -1. */
    protected int timeLife;

    /** Autorise ou non l'entrée du robot dans un obstacle */
    protected boolean entryObstacleAllowed;

    /** Indique si un point est dans un obstacle */
    public abstract boolean isInObstacle(Vec2 point);

    public abstract boolean intersect(Segment segment);

    /** Indique si deux obstacles sont égaux */
    public abstract boolean equals(Obstacle obstacle);

    /** Génére un hashCode à partir de l'obstacle. Utile pour les hashMap */
    public abstract int hashCode();

    public abstract String toString();

}
