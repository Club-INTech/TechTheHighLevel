package utils.math;

import java.util.ArrayList;

/**
 * Il s'agit d'une classe pour modéliser les obstacles rectangulaires en respectant notre modélisation du robot : On modélise le robot par un point et on grossit
 * tous les obstacles du rayon du robot
 *
 *               _____________
 *               |            |
 *               |R    (0)    |
 *          __R_0|____________|1__R___
 *         |     |    l       |      |
 *  *      |     |            |      |
 *  *      |  (3)|            |      |
 *  *      |   L |            | (1)  |
 *         |     |            |      |
 *         |     |            |      |
 *        _|R____|____________|__R___|
 *              3|            |2
 *              R|     (2)    |R
 *               |___________ |
 *
 * On relie les arcs de cercles
 */
public class CircularRectangle extends Shape {

    /**
     * Rayon des angles
     */
    private float radius;

    /**
     * Rectangle interieur
     */
    private Rectangle mainRectangle;

    /**
     * Arc de cercles repésentant les angles
     */
    private ArrayList<Circle> arcsDeCercle;

    /**
     * Les 4 rectangles entourant le principal
     */
    private ArrayList<Rectangle> smallRectangles;

    /**
     * Construit un rectangle à angles arrondis
     * @param centre    le centre du rectangle
     * @param length    longueur (en x) du rectangle principal
     * @param width     largeur (en y) du rectangle principal
     * @param radius    rayon des angles
     */
    public CircularRectangle(Vec2 centre, float length, float width, float radius) {
        super(centre);
        this.mainRectangle = new Rectangle(centre, length, width);
        this.radius = radius;
        this.arcsDeCercle = new ArrayList<>();
        //Pour la numérotation des arcs de cercles (voir le schéma ci dessus)
        for(int i =0; i<4; i++){
            arcsDeCercle.add(new Circle(this.mainRectangle.getPoints().get(i),this.radius,0,Math.PI/2));
        }
        this.smallRectangles = new ArrayList<>();
        int mainRectangleX = mainRectangle.getCenter().getX();
        int mainRectangleY = mainRectangle.getCenter().getY();
        float mainRectangleL=mainRectangle.getLength();
        float mainRectanglel=mainRectangle.getWidth();
        //Pour la numérotation des petits rectangles : voir schéma ci-dessus
        Vec2 smallRectanglecenter0=new VectCartesian(mainRectangleX,mainRectangleY + mainRectangleL/2 + this.radius);
        Vec2 smallRectanglecenter1=new VectCartesian(mainRectangleX + mainRectanglel/2 + this.radius/2,mainRectangleY);
        Vec2 smallRectanglecenter2=new VectCartesian(mainRectangleX ,mainRectangleY - mainRectangleL/2 - this.radius/2);
        Vec2 smallRectanglecenter3=new VectCartesian(mainRectangleX -mainRectanglel/2 - this.radius/2  ,mainRectangleY);
        smallRectangles.add(new Rectangle(smallRectanglecenter0,mainRectanglel,this.radius));
        smallRectangles.add(new Rectangle(smallRectanglecenter1,mainRectangleL,this.radius));
        smallRectangles.add(new Rectangle(smallRectanglecenter2,mainRectanglel,this.radius));
        smallRectangles.add(new Rectangle(smallRectanglecenter3,mainRectangleL,this.radius));
    }

    @Override
    public boolean intersect(Segment segment) {
        return false;
    }

    @Override
    public boolean isInShape(Vec2 point) {
        return false;
    }

    @Override
    public Shape clone() throws CloneNotSupportedException {
        return null;
    }

    @Override
    public boolean equals(Object object) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return null;
    }
}
