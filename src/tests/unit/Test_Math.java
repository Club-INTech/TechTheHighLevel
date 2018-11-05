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
    public void circleIntersectsWithSegment(){
        circle=new Circle(new VectCartesian(2,5),10);
        segment=new Segment(new VectCartesian(5,10), new VectCartesian(4,5));
        Assert.assertTrue(circle.intersect(segment));
    }


    @Test
    public void circleContainsCircle(){
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
    public void circleContainsPoint(){
        circle=new Circle(new VectCartesian(0,0),5);
        VectCartesian vecteur=new VectCartesian(0,5);
        Assert.assertTrue(circle.isInShape(vecteur));
    }

    @Test
    public void closestPointToCircle(){
        circle=new Circle(new VectCartesian(5,0),5);
        Vec2 closestPoint=circle.closestPointToCircle(new VectCartesian(5,6));
        System.out.println(closestPoint);
    }

    @Test
    public void rectangleIntersectsWithSegment(){
        rectangle=new Rectangle(new VectCartesian(0,0),5,3);
        segment=new Segment(new VectCartesian(0,0),new VectCartesian(7,9));
        Assert.assertTrue(rectangle.intersect(segment));
    }

    @Test
    public void rectangleContainsCircle(){
        rectangle=new Rectangle(new VectCartesian(0,0),5,3);
        circle=new Circle(new VectCartesian(0,0),5);
        //Assert.assertTrue(rectangle.containsCircle(circle));
    }

    @Test
    public void segmentIntersectsWithSegment(){
        Segment segment1=new Segment(new VectCartesian(0,0), new VectCartesian(0,2));
        Segment segment2=new Segment(new VectCartesian(0,0), new VectCartesian(0,4));
        Assert.assertTrue(segment1.intersect(segment2));
    }

    @Test
    public void segmentDistanceTopoint(){
        segment=new Segment(new VectCartesian(0,0),new VectCartesian(0,5));
        System.out.println(segment.distanceToPoint(new VectCartesian(3,3)));
    }



}
