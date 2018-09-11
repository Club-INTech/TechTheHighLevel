package math;

import java.util.ArrayList;

public class Circle extends Shape {

    /**rayon du cercle*/
    private float radius;

    /**angle où l'arc de cercle commence*/
    private float angleStart;

    /**angle où l'arc de cercle se termine*/
    private float angleEnd;

    /**Constructeur d'un cercle*/
    public Circle(int xCenter, int yCenter,float radius) {
        super(xCenter, yCenter);
        this.radius=radius;
    }

    /**Constructeur d'un arc de cercle*/
    public Circle(int xCenter, int yCenter,float radius,float angleStart, float angleEnd) {
        super(xCenter, yCenter);
        this.radius=radius;
        this.angleStart=angleStart;
        this.angleEnd=angleEnd;
    }


    /**Cette méthode retourne true s'il y'a intersection centre le cercle et le segment*/
    @Override
    public boolean intersectsWithSegment(Segment segment) {
        return false;
    }

    /**cette méthode retourne true si notre objet cercle contient un autre(celui passé en pramètre)*/
    @Override
    public boolean containsCircle(Circle circle) {
        return false;
    }

    /**
     * Cette méthode retourne true s'il y'a une intersection entre les deux cercles
     * @param circle
     * @return
     */
    public boolean intersectsWithCircle(Circle circle){
        return false;
    }

    /**
     * Cette méthode prend en paramètre un point et retourne le point du cercle le plus proche de ce point
     * @param point
     * @return
     */
    public Vec2 closestPointToCircle(Vec2 point){
        return null;
    }

    /**
     * Cette méthode retourne une liste de points autour d'un cercle, utile pour la construction de noeuds d'un graphe autour d'obstacles circulaires ;)
     * @param n : nombre de points qu'on veut autour du cercle
     * @return
     */
    public ArrayList<Vec2> pointAroundCircle(int n){
        return null;
    }

    /**getter du rayon*/
    public float getRadius() {
        return radius;
    }

    /**setter du rayon*/
    public void setRadius(float radius) {
        this.radius = radius;
    }
}
