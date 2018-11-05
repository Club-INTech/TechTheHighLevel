package utils.math;

import java.util.ArrayList;

/**
 * Définit un rectangle
 *
 *           A______0______B
 *           |             |
 *           |             |
 *          3|             |
 *           |             |1
 *   y       |             |
 *   ^       |             |
 *   |       |_____________|
 *   |       D      2       C
 *   |--> x
 * @see Shape
 *
 * @author yousra
 */
public class Rectangle extends Shape {

    /**
     * Longueur du rectangle (en x)
     */
    private float length;

    /**
     * Largeur du rectangle (en y)
     */
    private float width;

    /**
     * Segments délimitant le rectangle
     */
    private ArrayList<Segment> segments;

    /**
     * Construit un rectangle
     * @param centre    centre du rectangle
     * @param length    longueur (x)
     * @param width     largeur (y)
     */
    public Rectangle(Vec2 centre, float length, float width) {
        super(centre);
        this.length = length;
        this.width = width;

        Vec2 hg = center.plusVector(new VectCartesian(-length/2, width/2));
        Vec2 hd = center.plusVector(new VectCartesian(length/2, width/2));
        Vec2 bg = center.plusVector(new VectCartesian(-length/2, -width/2));
        Vec2 bd = center.plusVector(new VectCartesian(length/2, -width/2));
        this.segments = new ArrayList<>();

        segments.add(new Segment(hd, hg));
        segments.add(new Segment(hg, bg));
        segments.add(new Segment(bg, bd));
        segments.add(new Segment(bd, hd));
    }

    /**
     * Cette méthode retourne vrai s'il y'a intersection entre le segment et le rectangle:
     * Il y'a intersection entre un segment et un rectangle s'il y'a intersection entre ce segment
     * et l'un des quatre segments du rectangle ou si le segment est dans le rectangle
     * @see Shape
     */
    @Override
    public boolean intersect(Segment segment) {
        for (Segment seg : segments) {
            if (seg.intersect(segment)) {
                return true;
            }
        }
        return isInShape(segment.getPointA()) && isInShape(segment.getPointB());
    }

    /**
     * Cette méthode retourne true si notre rectangle contient un point
     * @param point point
     * @return      true si le rectangle contient un point
     */
    @Override
    public boolean isInShape(Vec2 point){
        return Math.abs(point.getX() - center.getX()) < this.length/2 &&
                Math.abs(point.getY() - center.getY()) < this.width/2;
    }

    /**
     * Cette méthode retourne les diagonales d'un rectangle
     * @return  les diagonales du rectangle
     */
    public ArrayList<Segment> getDiagonals(){
        ArrayList<Segment> diagonals = new ArrayList<>();
        diagonals.add(new Segment(segments.get(0).getPointA(), segments.get(1).getPointB()));
        diagonals.add(new Segment(segments.get(3).getPointA(), segments.get(0).getPointB()));
        return diagonals;
    }

    /**
     * Cette méthode retourne les segments d'un rectangle
     *       (0) A______________B (1)
     *           |             |
     *           |             |
     *           |             |
     *           |             |
     *           |             |
     *           |             |
     *           |_____________|
     *        (3) D              C (2)
     * @return A B C D
     */
    public ArrayList<Vec2> getPoints(){
        ArrayList<Vec2> points = new ArrayList<>();
        points.add(segments.get(0).getPointA());
        points.add(segments.get(1).getPointA());
        points.add(segments.get(2).getPointA());
        points.add(segments.get(3).getPointA());
        return points;
    }

    @Override
    /** On vérifie si le rectangle a le même centre, la même largeur et la même longueur */
    public boolean equals(Object obj) {
        if (obj instanceof Rectangle){
            return ((Rectangle) obj).getCenter().equals(this.getCenter()) && ((Rectangle) obj).getLength()==this.getLength() && ((Rectangle) obj).getWidth()==this.getWidth();
        }
        return false;
    }

    @Override
    public Shape clone() throws CloneNotSupportedException {
        return new Rectangle(this.center.clone(), this.length, this.width);
    }

    @Override
    public int hashCode() {
        return (int) (this.center.hashCode() + this.length + this.width*1000);
    }

    @Override
    public String toString() {
        return "rectangle [center : " + center.toString() + ", length (x) : " + this.length + ", width (y) : " + this.width + "]";
    }

    /**
     * Getters
     */
    public float getLength() {
        return length;
    }
    public float getWidth() {
        return width;
    }
}
