package utils.maths;

/**
 * Classe abstraite désignant un archétype d'un forme
 */
public abstract class Shape implements Cloneable {
    /**
     * Centre de la forme
     */
    protected Vector center;

    /**
     * Intersection avec un segment
     * @param segment   segment à tester
     * @return  true si intersection
     */
    public abstract boolean intersect(Segment segment);

    /**
     * @param point point à tester
     * @return  true si le point est dans la forme
     */
    public abstract boolean isInShape(Vector point);

    /**
     * @see Object#clone()
     */
    @Override
    public abstract Shape clone() throws CloneNotSupportedException;

    /**
     * @see Object#equals(Object)
     */
    @Override
    public abstract boolean equals(Object object);

    /**
     * @see Object#toString()
     */
    @Override
    public abstract String toString();

    /**
     * Setters & Getters
     */
    public Vector getCenter() {
        return this.center;
    }
    public void setCenter(Vector center) {
        this.center = center;
    }
}
