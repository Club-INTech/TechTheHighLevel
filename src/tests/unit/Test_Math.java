package unit;

import org.junit.Assert;
import org.junit.Test;
import utils.math.*;

public class Test_Math {

    private Circle circle;

    private Rectangle rectangle;

    private Segment segment;

    private Shape shape;

    private Vec2 vec2;

    private VectCartesian vectCartesian;

    private VectPolar vectPolar;

    @Test
    public void vec2Init() {

    }

    @Test
    public void vec2PlusMinus() {

    }

    @Test
    public void vec2DotCross() {

    }

    @Test
    public void vec2DistanceTo() {

    }

    @Test
    public void vec2Symetrize() {

    }

    @Test
    public void vec2Equals() {

    }

    @Test
    public void segmentIntersect() {
        Segment segment1 = new Segment(new VectCartesian(0,0), new VectCartesian(0,2));
        Segment segment2 = new Segment(new VectCartesian(0,0), new VectCartesian(0,4));
        Assert.assertTrue(segment1.intersect(segment2));
    }

    @Test
    public void segmentDistanceTo() {
        segment = new Segment(new VectCartesian(0,0),new VectCartesian(0,5));
        System.out.println(segment.distanceTo(new VectCartesian(3,3)));
    }

    @Test
    public void segmentVecteurDirecteur() {

    }

    @Test
    public void segmentEquals() {

    }

    @Test
    public void circleIntersect(){
        circle=new Circle(new VectCartesian(2,5),10);
        segment=new Segment(new VectCartesian(5,10), new VectCartesian(4,5));
        Assert.assertTrue(circle.intersect(segment));
    }


    @Test
    public void circleIsInShape(){
        circle=new Circle(new VectCartesian(0,0),5);
        Circle circle2=new Circle(new VectCartesian(1,0),85);
        // Assert.assertTrue(circle2.containsCircle(circle));
    }

    @Test
    public void circleIntersectsWithCircle(){
        circle=new Circle(new VectCartesian(0,0),5);
        Circle circle2=new Circle(new VectCartesian(0,0),5);
        Assert.assertTrue(circle2.intersectsWithCircle(circle));
    }

    @Test
    public void circleClosestPointAroundCircle() {

    }

    @Test
    public void circleEquals() {

    }

    @Test
    public void rectangleIntersect(){
        rectangle=new Rectangle(new VectCartesian(0,0),5,3);
        segment=new Segment(new VectCartesian(0,0),new VectCartesian(7,9));
        Assert.assertTrue(rectangle.intersect(segment));
    }

    @Test
    public void rectangleIsInShape(){
        rectangle=new Rectangle(new VectCartesian(0,0),5,3);
        circle=new Circle(new VectCartesian(0,0),5);
        //Assert.assertTrue(rectangle.containsCircle(circle));
    }

    @Test
    public void rectangleGetDiagonalsAndPoints() {

    }

    @Test
    public void rectangleEquals() {

    }

    @Test
    public void circularRectangleIsInShape() {

    }

    @Test
    public void circularRectangleIntersect() {

    }

    @Test
    public void circularRectangleEquals() {

    }
}
