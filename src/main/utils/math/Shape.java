package utils.math;

/**
 * Il s'agit d'une classe abstraite définissant un cadre pour créer des formes
 */
public abstract class Shape {

    private Vec2 center;


    /**Constructeur
     * @param center centre */
    protected Shape(Vec2 center) {
        this.center =center;
    }



    /**
     * Cette méthode retourne true s'il y'a intersection avec un segment
     * @param segment
     * @return
     */
    public abstract boolean intersectsWithSegment(Segment segment);

    /**
     * Cette méthode retourne true si le shape en question (cercle ou rectangle) contient un cercle
     * @param circle
     * @return
     */
    public abstract boolean containsCircle(Circle circle);

    /**
     * Cette méthode retourne la distance entre le centre du shape et le point en question
     * @param vect vecteur en coordonneés
     * @return
     */
    public double distanceToPoint(Vec2 vect){
        return this.center.distanceTo(vect);
    }

    /**
     * Getter du centre
     * @return
     */
    public Vec2 getCenter(){
        return this.center;
    }



}
