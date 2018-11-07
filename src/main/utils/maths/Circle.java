package utils.maths;

import java.awt.geom.Line2D;

/**
 * Classe représentant un cercle
 */
public class Circle extends Shape
{
    /**
     * Rayon
     */
    private int ray;

    /**
     * Construit un cercle !
     */
    public Circle(Vector center, int ray) {
        this.center = center;
        this.ray = ray;
    }

    /**
     * @see Shape#intersect(Segment)
     */
    @Override
    public boolean intersect(Segment segment) {
        return Line2D.ptSegDist(segment.getA().getX(), segment.getA().getY(),
                segment.getB().getX(), segment.getB().getY(),
                this.getCenter().getX(), this.getCenter().getY())
                <= this.ray;
    }

    /**
     * @see Shape#isInShape(Vector)
     */
    @Override
    public boolean isInShape(Vector point) {
        return center.withdrawNewVector(point).getRay() <= ray;
    }

    /**
     * @see Object#clone()
     */
    @Override
    public Circle clone() throws CloneNotSupportedException {
        return new Circle(this.center.clone(), this.ray);
    }

    /**
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof Circle){
            return this.center.equals(((Circle) object).getCenter()) && this.ray == ((Circle) object).ray;
        }
        return false;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "circle [center : " + center.toString() + ", ray : " + this.ray + "]";
    }

    /**
     * Getters & Setters
     */
    public int getRay() {
        return ray;
    }
    public void setRay(int ray) {
        this.ray = ray;
    }
}
