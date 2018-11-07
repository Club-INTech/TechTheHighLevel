package unitaires;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import utils.maths.*;

public class Test_Maths
{
    /**
     * Quelques objets...
     */
    private Circle circle;
    private Rectangle rectangle;
    private Segment segment;
    private double delta = 0.0001;

    @After
    public void tearDown() {
        circle = null;
        segment = null;
    }

    @Test
    public void testModulo() {
        Assert.assertEquals(MathLib.modulo(3*Math.PI, 2*Math.PI), -Math.PI, delta);
        Assert.assertEquals(MathLib.modulo(-5*Math.PI, 2*Math.PI), -Math.PI, delta);
        Assert.assertEquals(MathLib.modulo(7*Math.PI/2, 2*Math.PI), -Math.PI/2, delta);
    }

    @Test
    public void testIntersectCircle() {
        circle = new Circle(new Vector(0, 0), 25);
        segment = new Segment(new Vector(-12, -8), new Vector(58, 47));
        Assert.assertTrue(circle.intersect(segment));

        segment = new Segment(new Vector(-25, -30), new Vector(-25, 30));
        Assert.assertTrue(circle.intersect(segment));

        segment = new Segment(new Vector(-25, 30), new Vector(-25, -30));
        Assert.assertTrue(circle.intersect(segment));

        segment = new Segment(new Vector(-25, 26), new Vector(30, 26));
        Assert.assertFalse(circle.intersect(segment));
    }

    @Test
    public void testIsInCircle() {
        circle = new Circle(new Vector(5, 5), 20);
        Vector point = new Vector(0, 8);
        Assert.assertTrue(circle.isInShape(point));

        point = new Vector(0, 25);
        Assert.assertFalse(circle.isInShape(point));
    }

    @Test
    public void testIntersectRectangle() {
        rectangle = new Rectangle(new Vector(10, 20), 100, 200);
        segment = new Segment(new Vector(-50, -10), new Vector(0, 400));
        Assert.assertTrue(rectangle.intersect(segment));

        segment = new Segment(new Vector(-100, -10), new Vector(-150, 200));
        Assert.assertFalse(rectangle.intersect(segment));

        segment = new Segment(new Vector(0, 50), new Vector(30, 120));
        Assert.assertTrue(rectangle.intersect(segment));
    }
}
