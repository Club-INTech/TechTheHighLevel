package junit.unit;

import data.table.CircularObstacle;
import data.table.RectangularObstacle;
import org.junit.*;
import utils.math.*;


public class Test_Obstacle {

    private CircularObstacle circularObstacle;
    private RectangularObstacle rectangularObstacle;

    @Before
    public void setUp()
    {
        circularObstacle = new CircularObstacle(new Circle( new VectCartesian(0, 0), 42), 42, true);
        rectangularObstacle = new RectangularObstacle(new Rectangle(new VectCartesian(0,0),42, 42), 42, true);

        Assert.assertNotNull(circularObstacle);
        Assert.assertNotNull(rectangularObstacle);
    }

    @Test
    public void testCircularObstacleisInObstacle()
    {
        VectCartesian in = new VectCartesian(12, 12);
        VectCartesian out = new VectCartesian(666, 666);

        Assert.assertTrue(circularObstacle.isInObstacle(in));
        Assert.assertFalse(circularObstacle.isInObstacle(out));
    }

    @Test
    public void testCircularObstacleintersect()
    {
        Segment in = new Segment(new VectCartesian(0, 0), new VectCartesian(12, 12));
        Segment out = new Segment(new VectCartesian(600, 600), new VectCartesian(612, 612));

        Assert.assertTrue(circularObstacle.intersect(in));
        Assert.assertFalse(circularObstacle.intersect(out));
    }

    @Test
    public void testCircularObstacleequale()
    {
        CircularObstacle circularObstacletrue = new CircularObstacle(new Circle(new VectCartesian(0, 0), 42), 42, true);
        CircularObstacle circularObstaclefalse = new CircularObstacle(new Circle(new VectCartesian(666, 666), 42), 42, true);
        CircularObstacle circularObstaclefalse2 = new CircularObstacle(new Circle(new VectCartesian(0, 0), 12), 42, true);

        Assert.assertTrue(circularObstacle.equals(circularObstacletrue));
        Assert.assertFalse(circularObstacle.equals(circularObstaclefalse));
        Assert.assertFalse(circularObstacle.equals(circularObstaclefalse));
    }

    @Ignore
    @Test
    public void testRectangularObstacleisInObstacle()
    {
        VectCartesian in = new VectCartesian(12, 12);
        VectCartesian out = new VectCartesian(666, 666);

        Assert.assertTrue(rectangularObstacle.isInObstacle(in));
        Assert.assertFalse(rectangularObstacle.isInObstacle(out));
    }

    @After
    public void tearDown()
    {
        circularObstacle = null;
        rectangularObstacle = null;
    }
}
