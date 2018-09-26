package junit.unit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.math.*;

public class Test_math {


    private Circle circle;

    private Rectangle rectangle;

    private Segment segment;

    private Shape shape;

    private Vec2 vec2;

    private VectCartesian vectCartesian;

    private VectPolar vectPolar;


    @Test
    public void circleIntersectsWithSegment(){

        circle=new Circle(2,5,10);
        segment=new Segment(new VectCartesian(5,10), new VectCartesian(4,5));
        Assert.assertTrue(circle.intersectsWithSegment(segment));
    }


    @Test
    public void circleContainsCircle(){
        circle=new Circle(0,0,5);
        Circle circle2=new Circle(1,0,85);

        Assert.assertTrue(circle2.containsCircle(circle));
    }

    @Test
    public void circleIntersectsWithCircle(){
        circle=new Circle(0,0,5);
        Circle circle2=new Circle(1,4,5);
        Assert.assertTrue(circle2.intersectsWithCircle(circle));

    }




    @Test
    public void circleContainsPoint(){
        circle=new Circle(0,0,5);
        VectCartesian vecteur=new VectCartesian(0,5);
        Assert.assertTrue(circle.containsPoint(vecteur));
    }

    @Test
    public void closestPointToCircle(){

    }

    @Test
    public void rectangleIntersectsWithSegment(){

    }

    @Test
    public void rectangleContainsCircle(){

    }

    @Test
    public void rectangleIntersectsWithRectangle(){

    }

    @Test
    public void rectangleGetSegments(){

    }

    @Test
    public void rectangleGetDiagonals(){

    }

    @Test
    public void segmentIntersectsWithSegment(){

    }

    @Test
    public void segmentDistanceTopoint(){

    }

    @Test
    public void segmentVecteurDirecteur(){

    }

    @Test
    public void shapeDistanceToPoint(){

    }

    @Test
    public void Vect2AnglePolaire(){

    }



}
