package validation;

import data.table.MobileCircularObstacle;
import data.table.StillCircularObstacle;
import data.table.StillRectangularObstacle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.math.Circle;
import utils.math.Vec2;
import utils.math.VectCartesian;

public class Test_Obstacle {
    /**
     * Obstacle à teser
     */
    private StillCircularObstacle fixedCircular;
    private StillRectangularObstacle fixedRectangular;
    private MobileCircularObstacle mobileCircular;

    @Before
    public void setUp() {
        fixedCircular = new StillCircularObstacle(new VectCartesian(20, 20), 20);
        fixedRectangular = new StillRectangularObstacle(new VectCartesian(10, 10), 20, 40);
        mobileCircular = new MobileCircularObstacle(new VectCartesian(30, -10), 25);
    }

    @Test
    public void testIsInObstacle() {
        Vec2 point = new VectCartesian(32, 32);
        Assert.assertTrue(fixedCircular.isInObstacle(point));
        Assert.assertFalse(fixedRectangular.isInObstacle(point));
        Assert.assertFalse(mobileCircular.isInObstacle(point));

        point = new VectCartesian(3, -5);
        Assert.assertFalse(fixedCircular.isInObstacle(point));
        Assert.assertTrue(fixedRectangular.isInObstacle(point));
        Assert.assertFalse(mobileCircular.isInObstacle(point));

        point = new VectCartesian(26, -14);
        Assert.assertFalse(fixedCircular.isInObstacle(point));
        Assert.assertFalse(fixedRectangular.isInObstacle(point));
        Assert.assertTrue(mobileCircular.isInObstacle(point));

        point = new VectCartesian(19, 3);
        Assert.assertTrue(fixedCircular.isInObstacle(point));
        Assert.assertTrue(fixedRectangular.isInObstacle(point));
        Assert.assertTrue(mobileCircular.isInObstacle(point));

        point = new VectCartesian(-5, 22);
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
        mobileCircular.update(new VectCartesian(40, 65));
        Assert.assertEquals(mobileCircular.getPosition(), new VectCartesian(40, 65));
        Assert.assertEquals(mobileCircular.getShape(), new Circle(new VectCartesian(40, 65), 25));
        Assert.assertTrue(mobileCircular.getOutDatedTime() > System.currentTimeMillis() &&
                mobileCircular.getOutDatedTime() <= System.currentTimeMillis() + MobileCircularObstacle.getDefaultLifeTime());
    }
}
