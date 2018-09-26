package utils.math;

import java.awt.geom.Line2D;

public class Segment {


    /**Premier point du segment*/
    private Vec2 pointA;

    /**Deuxième point du segment*/
    private Vec2 pointB;

    /**Constructeur*/
    public Segment(Vec2 pointA, Vec2 pointB) {
        this.pointA = pointA;
        this.pointB = pointB;
    }

    /**cette méthode détermine si deux droites sont parallèles ou non*/
    public boolean intersectsWithSegment(Segment segment){
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

    /**Cette méthode détermine la distance entre une droite et un point
     * */
    public double distanceToPoint(Vec2 point){
        if(pointA.getX()==pointB.getX()){
            return Math.abs(point.getX() - pointA.getX() );
        }
        else{
            double a=(pointB.getY() - pointA.getY() )/ (pointB.getX() - pointA.getX());
            double b= pointB.getY() - a*pointB.getX();
            return Math.abs(point.getY() - a*point.getX() - b)/Math.sqrt(1 + a*a);
        }
    }

    /**Cette méthode retourne la longueur d'un segment*/
    public double longueurSegment(){
        int xB=pointB.getX();
        int yB=pointB.getY();
        int xA=pointA.getX();
        int yA=pointA.getY();
        return Math.sqrt((xB - xA)*(xB - xA) + (yB - yA)*(yB - yA) );
    }

    /**Cette méthode détermine le vecteur directeur d'une droite*/
    public Vec2 vecteurDirecteur(){
        int a;
        int b;
        int xA=this.getPointA().getX();
        int xB=this.getPointB().getX();
        int yA=this.getPointA().getY();
        int yB=this.getPointB().getY();
        //Il s'agit d'une droite parallèle à l'axe des ordonnées, la valeur de a peut être qcq
        if(xA==xB){
            b=xA;
            a=1;
            return new Vec2(b,a);
        }
        else{
            //Il s'agit d'une droite parallèle à l'axe des abcisses
            if(yA==yB){
                a=0;
                b=yA;
                return new Vec2(a,b);
            }
            //Cas général
            else{
                a=(yB-yA)/(xB-xA);
                b=(yA*xB-xA*yB)/(xB-xA);
                return new Vec2(-b,a);
            }
        }
    }

    public Vec2 getPointA() {
        return pointA;
    }

    public Vec2 getPointB() {
        return pointB;
    }

    public void setPointA(Vec2 pointA) {
        this.pointA = pointA;
    }

    public void setPointB(Vec2 pointB) {
        this.pointB = pointB;
    }
}
