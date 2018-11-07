package utils.maths;

import java.awt.geom.Line2D;

/**
 * Classe représentant un segment...
 */
public class Segment implements Cloneable
{
    /**
     * Points délimitant le segment
     */
    private Vector a;
    private Vector b;

    /**
     * Construit un segment
     */
    public Segment(Vector a, Vector b) {
        this.a = a;
        this.b = b;
    }

    /**
     * Intersection avec un autre segment
     */
    public boolean intersect(Segment other) {
        return Line2D.linesIntersect(this.a.getX(), this.a.getY(),
                this.b.getX(), this.b.getY(),
                other.a.getX(), other.a.getY(),
                other.b.getX(), other.b.getY());
    }

    /**
     * @see Object#clone()
     */
    @Override
    public Segment clone() throws CloneNotSupportedException {
        Segment copy = (Segment) super.clone();
        copy.setA(a.clone());
        copy.setB(b.clone());
        return copy;
    }

    /**
     * Getters & Setters
     */
    public Vector getA() {
        return a;
    }
    public void setA(Vector a) {
        this.a = a;
    }
    public Vector getB() {
        return b;
    }
    public void setB(Vector b) {
        this.b = b;
    }
}
