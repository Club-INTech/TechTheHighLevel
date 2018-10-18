package utils.math;

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

    private float radius;
    private Rectangle mainRectangle;
    private Circle[] arcsDeCercle;
    private Rectangle[] smallRectangles;

    public CircularRectangle(Vec2 centre, float length, float width, float radius) {
        super(centre);
        this.mainRectangle=new Rectangle(centre,length,width);
        this.radius=radius;
        this.arcsDeCercle=new Circle[4];
        //Pour la numérotation des arcs de cercles (voir le schéma ci dessus)
        for(int i =0; i<4; i++){
            arcsDeCercle[i]=new Circle(this.mainRectangle.getPoints()[i],this.radius,0,Math.PI/2);
        }
        this.smallRectangles=new Rectangle[4];
        int mainRectangleX=mainRectangle.getCenter().getX();
        int mainRectangleY=mainRectangle.getCenter().getY();
        float mainRectangleL=mainRectangle.getLength();
        float mainRectanglel=mainRectangle.getWidth();
        //Pour la numérotation des petits rectangles : voir schéma ci-dessus
        Vec2 smallRectanglecenter0=new VectCartesian(mainRectangleX,mainRectangleY + mainRectangleL/2 + this.radius);
        Vec2 smallRectanglecenter1=new VectCartesian(mainRectangleX + mainRectanglel/2 + this.radius/2,mainRectangleY);
        Vec2 smallRectanglecenter2=new VectCartesian(mainRectangleX ,mainRectangleY - mainRectangleL/2 - this.radius/2);
        Vec2 smallRectanglecenter3=new VectCartesian(mainRectangleX -mainRectanglel/2 - this.radius/2  ,mainRectangleY);
        smallRectangles[0]=new Rectangle(smallRectanglecenter0,mainRectanglel,this.radius);
        smallRectangles[1]=new Rectangle(smallRectanglecenter1,mainRectangleL,this.radius);
        smallRectangles[2]=new Rectangle(smallRectanglecenter2,mainRectanglel,this.radius);
        smallRectangles[3]=new Rectangle(smallRectanglecenter3,mainRectangleL,this.radius);
    }

    @Override
    public boolean intersectsWithSegment(Segment segment) {
        return false;
    }

    @Override
    public boolean containsCircle(Circle circle) {
        return false;
    }

    @Override
    public double distanceToPoint(Vec2 vect) {
        return super.distanceToPoint(vect);
    }

    @Override
    public Vec2 getCenter() {
        return super.getCenter();
    }


}
