package utils.maths;

import java.util.ArrayList;

/**
 * Implémente les rectangles
 */
public class Rectangle extends Shape
{
    /**
     * Segments formant le rectangle
     */
    private ArrayList<Segment> segments;

    /**
     * Longueur du rectangle (en x)
     */
    private int length;

    /**
     * Largeur du rectangle (en y)
     */
    private int width;

    /**
     * Construit un rectangle
     */
    public Rectangle(Vector center, int length, int width) {
        this.center = center;
        this.length = length;
        this.width = width;
        Vector hg = center.addNewVector(new Vector(-length/2, width/2));
        Vector hd = center.addNewVector(new Vector(length/2, width/2));
        Vector bg = center.addNewVector(new Vector(-length/2, -width/2));
        Vector bd = center.addNewVector(new Vector(length/2, -width/2));

        segments = new ArrayList<>();
        segments.add(new Segment(hd, hg));
        segments.add(new Segment(hg, bg));
        segments.add(new Segment(bg, bd));
        segments.add(new Segment(bd, hd));
    }

    /**
     * @see Shape#intersect(Segment)
     */
    @Override
    public boolean intersect(Segment segment) {
        for (Segment seg : segments) {
            if (seg.intersect(segment)) {
                return true;
            }
        }
        return isInShape(segment.getA()) && isInShape(segment.getB());
    }

    /**
     * @see Shape#isInShape(Vector)
     */
    @Override
    public boolean isInShape(Vector point) {
        Vector vec = this.center.withdrawNewVector(point);
        return Math.abs(vec.getX()) < this.length/2 && Math.abs(vec.getY()) < this.width/2;
    }

    /**
     * @see Object#clone()
     */
    @Override
    public Rectangle clone() throws CloneNotSupportedException {
        return new Rectangle(this.center.clone(), this.length, this.width);
    }

    @Override
    public boolean equals(Object object) {
        return false;
    }

    @Override
    public String toString() {
        return "rectangle [center : " + center.toString() + ", length (x) : " + this.length + ", width (y) : " + this.width + "]";
    }

    /**
     * Getters & Setters
     */
    public int getLength() {
        return length;
    }
    public int getWidth() {
        return width;
    }
}
