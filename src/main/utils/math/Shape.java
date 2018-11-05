package utils.math;

/**
 * Il s'agit d'une classe abstraite définissant un cadre pour créer des formes
 *
 * @author yousra, rem
 */
public abstract class Shape implements Cloneable {
    /**
     * Centre de la forme
     */
    protected Vec2 center;

    /**
     * Constructeur
     * @param center    centre
     */
    protected Shape(Vec2 center) {
        this.center =center;
    }

    /**
     * @param segment   segment
     * @return  true s'il y'a intersection avec le segment
     */
    public abstract boolean intersect(Segment segment);

    /**
     * @param point point
     * @return  true si le point se trouve dans la forme
     */
    public abstract boolean isInShape(Vec2 point);

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
     * Getter & Setter
     */
    public Vec2 getCenter(){
        return this.center;
    }
    public void setCenter(Vec2 newCenter) {
        this.center = newCenter;
    }
}
