package utils.math;

import java.awt.geom.Line2D;
import java.util.ArrayList;

/**
 * @see utils.math.Shape
 */
public class Circle extends Shape {

    /**rayon du cercle*/
    private float radius;

    /**angle où l'arc de cercle commence*/
    private float angleStart;

    /**angle où l'arc de cercle se termine*/
    private float angleEnd;

    /**Constructeur d'un cercle
     * @see Shape
     * @param centre centre
     * @param radius rayon du cercle*/
    public Circle(Vec2 centre,float radius) {
        super(centre);
        this.radius=radius;
    }

    /**Constructeur d'un arc de cercle
     * @param centre centre du cercle
     * @param radius rayon du cercle
     * @param angleStart angle de début
     * @param angleEnd angle de fin*/
    public Circle(Vec2 centre,float radius,float angleStart, float angleEnd) {
        super(centre);
        this.radius=radius;
        this.angleStart=angleStart;
        this.angleEnd=angleEnd;
    }


    /**Cette méthode retourne true s'il y'a intersection centre le cercle et le segment
     * Elle se base sur une méthode de la librairie Line2D, le principe est le suivant :
     * Si on note (D) la droite qui porte le segment
     * (C) le cercle
     * On veut savoir si (D) et (C) sont en intersection
     * On note (P) la droite perpendiculaire à (D) qui passe par le centre de (C)
     * On peut donc connaitre les coordonnées du point de l'intersection entre (P) et (D) qu'on note I vu qu'on dispose de deux équations à deux inconnues
     * (D) et (C) sont en intersection ssi la distance entre I et le centre est inférieure à R le rayon du cercle
     * @param segment segment*/
    @Override
    public boolean intersectsWithSegment(Segment segment) {

        return Line2D.ptSegDist(segment.getPointA().getX(), segment.getPointA().getY(), segment.getPointB().getX(), segment.getPointB().getY(), this.getCenter().getX(), this.getCenter().getY())<=this.getRadius();
    }

    /**cette méthode retourne true si notre objet cercle (this) contient un autre(celui passé en paramètre), c'est-à-dire si la distance entre les deux centres est inférieure
     * au rayon de notre objet (on suppose ici que l'utilisateur veut savoir si son objet contient un cercle et non l'inverse
     * @param circle cercle*/
    @Override
    public boolean containsCircle(Circle circle) {
        if(this.getCenter().equals(circle.getCenter())){
            return circle.getRadius()<=this.getRadius();
        }
        else{
            return this.getCenter().distanceTo(circle.getCenter())<=this.getRadius() && circle.getRadius()<this.radius  ;
        }
    }

    /**
     * Cette méthode retourne true si deux cercles sont sécants, c'est-à-dire si la distance entre les deux centres est entre la valeur absolue de la différence des deux
     * rayons et la valeur absolue de la somme (valeur absolue ici pour la somme par sécurité, on sait jamais...)
     * @param circle cercle
     * @return
     */
    public boolean intersectsWithCircle(Circle circle){
        float r1=this.radius;
        float r2=circle.getRadius();
        //distance entre les deux centres
        double d=this.getCenter().distanceTo(circle.getCenter());
        return d>Math.abs(r2-r1) && d<Math.abs(r2+r1);
    }

    /**
     * Cette méthode prend en paramètre un point et retourne le point du cercle le plus proche de ce point
     * Le point le plus proche du cercle est le point appartenant au cercle et à la droite passante par le centre
     * du cercle et le point en paramètre
     * @param point point
     * @return
     */
    public Vec2 closestPointToCircle(Vec2 point){
        if (this.containsPoint(point)) {
            return point;
        }
        Vec2 vec = point.minusVector(this.getCenter());

        // Si la direction donnée par le vecteur point qui est hors du cercle intersecte l'arc de cercle, on a le point avec les coordonnées polaires
        if (vec.getA() >= this.getAngleStart() && vec.getA() <= this.getAngleEnd())
        {
            vec.setR(this.getRadius());
            return this.getCenter().plusVector(vec);
        }

        // Sinon, on doit choisir entre le point du début de l'arc de cercle et celui de fin
        else {
            Vec2 circleCenterStart = new Vec2(this.getRadius(), this.getAngleStart());
            Vec2 circleCenterEnd = new Vec2(this.getRadius(), this.getAngleEnd());

            if (this.getCenter().plusVector(circleCenterStart).distanceTo(point) >= this.getCenter().plusVector(circleCenterEnd).distanceTo(point)){
                return circleCenterEnd.plusVector(this.getCenter());
            }
            else{
                return circleCenterStart.plusVector(this.getCenter());
            }
        }

    }

    /**
     * Cette méthode retourne une liste de points autour d'un cercle, utile pour la construction de noeuds d'un graphe autour d'obstacles circulaires ;)
     * @param n : nombre de points qu'on veut autour du cercle
     * @return
     */
    public ArrayList<Vec2> pointsAroundCircle(int n){
        ArrayList<Vec2> l=new ArrayList<>();
        for(int i=0;i<n;i++){
            int x=(int)(this.getRadius()*Math.cos(2*i*Math.PI/n))+this.getCenter().getX();
            int y=(int)(this.getRadius()*Math.sin(2*i*Math.PI/n))+this.getCenter().getY();
            Vec2 vectoadd=new Vec2(x,y);
            l.add(vectoadd);
        }
        return l;
    }

    /**
     * Cette méthode retroune true si notre cercle contient un point
     * @param point
     * @return
     */
    public boolean containsPoint(Vec2 point){
        return this.getCenter().distanceTo(point)<=this.getRadius();
    }

    /**getter du rayon*/
    public float getRadius() {
        return radius;
    }

    /**setter du rayon*/
    public void setRadius(float radius) {
        this.radius = radius;
    }

    /**
     * getter de l'angle de début
     * @return
     */
    public float getAngleStart() {
        return angleStart;
    }

    /**
     * getter de l'angle de fin
     * @return
     */
    public float getAngleEnd() {
        return angleEnd;
    }

    /**
     * Setter de l'angle de début
     * @param angleStart
     */
    public void setAngleStart(float angleStart) {
        this.angleStart = angleStart;
    }

    /**
     * setter de l'angle de fin
     * @param angleEnd
     */
    public void setAngleEnd(float angleEnd) {
        this.angleEnd = angleEnd;
    }
}
