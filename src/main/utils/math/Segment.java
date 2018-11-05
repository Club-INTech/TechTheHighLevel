package utils.math;

import java.awt.geom.Line2D;

/**
 * Cette classe construit la structure segment : un segment est constitué de deux points
 *
 * @author yousra
 */
public class Segment {

    /**
     * Premier point du segment
     */
    private Vec2 pointA;

    /**
     * Deuxième point du segment
     */
    private Vec2 pointB;

    /**
     * Longueur du segment
     */
    private double length;

    /**
     * Constructeur
     * @param pointA pointA
     * @param pointB pointB
     */
    public Segment(Vec2 pointA, Vec2 pointB) {
        this.pointA = pointA;
        this.pointB = pointB;
        this.length = longueurSegment();
    }

    /**
     * @param segment segment
     * @return  true si intersection des segments
     */
    public boolean intersect(Segment segment){
        int xA1=this.getPointA().getX();
        int yA1=this.getPointA().getY();
        int xB1=this.getPointB().getX();
        int yB1=this.getPointB().getY();
        int xA2=segment.getPointA().getX();
        int yA2=segment.getPointA().getY();
        int xB2=segment.getPointB().getX();
        int yB2=segment.getPointB().getY();
        return Line2D.linesIntersect(xA1,yA1,xB1,yB1,xA2,yA2,xB2,yB2);
    }

    /**
     * Cette méthode détermine la distance entre une droite et un point
     * @param point point
     * @return  la distance entre le point et la droite qui porte le segment
     */
    public double distanceToPoint(Vec2 point){
        if(pointA.getX()==pointB.getX()){
            return Math.abs(point.getX() - pointA.getX() );
        }
        else{
            double a=(pointB.getY() - pointA.getY())/ (double)(pointB.getX() - pointA.getX());
            double b= pointB.getY() - a*pointB.getX();
            return Math.abs(point.getY() - a*point.getX() - b)/Math.sqrt(1 + a*a);
        }
    }

    /**
     * Cette méthode retourne la longueur d'un segment
     */
    private double longueurSegment(){
        int xB=pointB.getX();
        int yB=pointB.getY();
        int xA=pointA.getX();
        int yA=pointA.getY();
        return Math.sqrt((xB - xA)*(xB - xA) + (yB - yA)*(yB - yA));
    }

    /**
     * Cette méthode détermine le vecteur directeur d'une droite
     */
    public Vec2 vecteurDirecteur(){
        return pointA.minusVector(pointB);
    }

    /**
     * Getters & Setters
     */
    public Vec2 getPointA() {
        return pointA;
    }
    public Vec2 getPointB() {
        return pointB;
    }
    public void setPointA(Vec2 pointA) {
        this.pointA = pointA;
        this.length = longueurSegment();
    }
    public void setPointB(Vec2 pointB) {
        this.pointB = pointB;
        this.length = longueurSegment();
    }

    public double getLength() {
        return length;
    }

    @Override
    public String toString() {
        return String.format("[%s:%s]", this.pointA.toString(), this.pointB.toString());
    }

    @Override
    public Segment clone() {
        return new Segment(this.pointA.clone(),this.pointB.clone());
    }
}
