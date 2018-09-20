package utils.math;

public abstract class Shape {

    /**L'abscisse du centre*/
    private int xCenter;

    /** L'ordonnée du centre*/
    private int yCenter;

    /**Constructeur*/
    public Shape(int xCenter, int yCenter) {
        this.xCenter=xCenter;
        this.yCenter=yCenter;

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
     * @param xPoint : abscisse du point
     * @param yPoint : ordonnée du point
     * @return
     */
    public float distanceToPoint(int xPoint, int yPoint){
        return 0;
    }

    /**getter de l'abscisse du centre*/
    public int getxCenter() {
        return xCenter;
    }

    /**getter de l'ordonnée du centre*/
    public int getyCenter() {
        return yCenter;
    }

    /**setter de l'abscisse du centre*/
    public void setxCenter(int xCenter) {
        this.xCenter = xCenter;
    }

    /**setter de l'ordonnée du centre*/
    public void setyCenter(int yCenter) {
        this.yCenter = yCenter;
    }
}
