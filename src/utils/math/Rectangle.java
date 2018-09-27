package utils.math;


public class Rectangle extends Shape {

    /** longueur du rectangle*/
    private float length;

    /**largeur du rectangle*/
    private float width;

    /**Constructeur*/
    public Rectangle(int xCenter, int yCenter, float length, float width) {
        super(xCenter, yCenter);
    }

    /**Cette méthode retourne vrai s'il y'a intersection entre le segment et le rectangle:
     * Il y'a intersection entre un segment et un rectangle s'il y'a intersection entre ce segment
     * et l'un des quatre segments du rectangle*/
    @Override
    public boolean intersectsWithSegment(Segment segment) {

        return this.getSegments()[0].intersectsWithSegment(segment) || this.getSegments()[1].intersectsWithSegment(segment) || this.getSegments()[2].intersectsWithSegment(segment) || this.getSegments()[3].intersectsWithSegment(segment) ;
    }

    /**Cette méthode retourne vrai si le rectangle contient le cercle*/
    @Override
    public boolean containsCircle(Circle circle) {
        return circle.getRadius()< width || circle.getRadius()<length;
    }


    /**
     * Cette méthode retroune true si notre rectange contient un point
     * @param point
     * @return
     */
    public boolean containsPoint(Vec2 point){
        return this.containsCircle(new Circle(point.getX(), point.getY(), 0));
    }

    /**
     * Cette méthode retourne true s'il y'a intersection entre deux rectangles
     * @param rectangle
     * @return
     */
    public boolean intersectsWithRectangle(Rectangle rectangle){

        return this.intersectsWithSegment(rectangle.getSegments()[0]) || this.intersectsWithSegment(rectangle.getSegments()[1]) || this.intersectsWithSegment(rectangle.getSegments()[2]) || this.intersectsWithSegment(rectangle.getSegments()[3]);
    }

    /**
     * Cette méthode retourne les diagonales d'un rectangle
     * @return
     */
    public Segment[] getDiagonals(){
        VectCartesian pointA= new VectCartesian(new Integer((int)(this.getxCenter() - width/2)),new Integer((int)(this.getyCenter() + length/2)));
        VectCartesian pointB=new VectCartesian(new Integer((int)(pointA.getX()+width)),pointA.getY());
        VectCartesian pointC=new VectCartesian(pointB.getX(), new Integer((int) (pointB.getY() - length)));
        VectCartesian pointD=new VectCartesian(pointA.getX(), new Integer((int)(pointA.getY() - length)));
        Segment[] segments=new Segment[2];
        segments[0]=new Segment(pointA,pointC);
        segments[1]=new Segment(pointB,pointD);
        return segments;
    }

    public Segment[] getSegments(){
        VectCartesian pointA= new VectCartesian(new Integer((int)(this.getxCenter() - width/2)),new Integer((int)(this.getyCenter() + length/2)));
        VectCartesian pointB=new VectCartesian(new Integer((int)(pointA.getX()+width)),pointA.getY());
        VectCartesian pointC=new VectCartesian(pointB.getX(), new Integer((int) (pointB.getY() - length)));
        VectCartesian pointD=new VectCartesian(pointA.getX(), new Integer((int)(pointA.getY() - length)));
        Segment[] segments=new Segment[4];
        segments[0]= new Segment(pointA,pointB);
        segments[1]=new Segment(pointB,pointC);
        segments[2]=new Segment(pointC,pointD);
        segments[3]=new Segment(pointD,pointA);
        return segments;
    }

    /**
     * Cette méthode change les dimensions du rectangle
     * @param length : longueur du rectangle
     * @param width: largeur du rectangle
     */
    public void changeDimensions(float length, float width){
        this.length=length;
        this.width=width;
    }

    /**getter de la longueur*/
    public float getLength() {
        return length;
    }

    /**getter de la largeur*/
    public float getWidth() {
        return width;
    }

    /**setter de la longueur*/
    public void setLength(float length) {
        this.length = length;
    }

    /**setter de la largeur*/
    public void setWidth(float width) {
        this.width = width;
    }
}
