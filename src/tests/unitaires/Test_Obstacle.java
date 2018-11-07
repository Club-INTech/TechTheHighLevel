package unitaires;

import data.table.FixedCircularObstacle;
import data.table.FixedRectangularObstacle;
import data.table.MobileCircularObstacle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.maths.Circle;
import utils.maths.Vector;

public class Test_Obstacle
{
    /**
     * Obstacle à teser
     */
    private FixedCircularObstacle fixedCircular;
    private FixedRectangularObstacle fixedRectangular;
    private MobileCircularObstacle mobileCircular;

    @Before
    public void setUp() {
        fixedCircular = new FixedCircularObstacle(new Vector(20, 20), 20);
        fixedRectangular = new FixedRectangularObstacle(new Vector(10, 10), 20, 40);
        mobileCircular = new MobileCircularObstacle(new Vector(30, -10), 25);
    }

    @Test
    public void testIsInObstacle() {
        Vector point = new Vector(32, 32);
        Assert.assertTrue(fixedCircular.isInObstacle(point));
        Assert.assertFalse(fixedRectangular.isInObstacle(point));
        Assert.assertFalse(mobileCircular.isInObstacle(point));

        point = new Vector(3, -5);
        Assert.assertFalse(fixedCircular.isInObstacle(point));
        Assert.assertTrue(fixedRectangular.isInObstacle(point));
        Assert.assertFalse(mobileCircular.isInObstacle(point));

        point = new Vector(26, -14);
        Assert.assertFalse(fixedCircular.isInObstacle(point));
        Assert.assertFalse(fixedRectangular.isInObstacle(point));
        Assert.assertTrue(mobileCircular.isInObstacle(point));

        point = new Vector(19, 3);
        Assert.assertTrue(fixedCircular.isInObstacle(point));
        Assert.assertTrue(fixedRectangular.isInObstacle(point));
        Assert.assertTrue(mobileCircular.isInObstacle(point));

        point = new Vector(-5, 22);
        Assert.assertFalse(fixedCircular.isInObstacle(point));
        Assert.assertFalse(fixedRectangular.isInObstacle(point));
        Assert.assertFalse(mobileCircular.isInObstacle(point));
    }

    @Test
    public void testIntersect() {
        // TODO
    }

    @Test
    public void testUpdateMobileObstacle() {
        mobileCircular.update(new Vector(40, 65));
        Assert.assertEquals(mobileCircular.getPosition(), new Vector(40, 65));
        Assert.assertEquals(mobileCircular.getShape(), new Circle(new Vector(40, 65), 25));
        Assert.assertTrue(mobileCircular.getOutDatedTime() > System.currentTimeMillis() &&
                mobileCircular.getOutDatedTime() <= System.currentTimeMillis() + MobileCircularObstacle.getDefaultLifeTime());
    }
}
