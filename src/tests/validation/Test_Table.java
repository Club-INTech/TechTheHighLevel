package validation;

import data.Table;
import data.table.CircularObstacle;
import data.table.Obstacle;
import data.table.RectangularObstacle;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.Container;
import utils.container.ContainerException;
import utils.math.Circle;
import utils.math.Rectangle;
import utils.math.VectCartesian;

import java.util.ArrayList;

public class Test_Table {

    private Container container;

    private Table table;

    @Before
    public void setUp() throws ContainerException {
        container = Container.getInstance("Master");
        this.table = container.getService(Table.class);
    }

    @After
    public void tearDown(){
        this.table = null;
        Container.resetInstance();
    }

    @Test
    public void addCircularFixedObstacles(){
        int sizeBefore = this.table.getFixedObstacles().size();
        this.table.addFixedObstacle(new CircularObstacle(new Circle(new VectCartesian(0,0),0),false));
        int sizeAfter = this.table.getFixedObstacles().size();
        Assert.assertEquals(sizeBefore+1, sizeAfter);

        ArrayList<Obstacle> obstacleArray = new ArrayList<>();
        obstacleArray.add(new CircularObstacle(new Circle(new VectCartesian(1000,0), 50),false));
        obstacleArray.add(new CircularObstacle(new Circle(new VectCartesian(0,1000), 50),false));
        obstacleArray.add(new CircularObstacle(new Circle(new VectCartesian(1000,1000), 50),false));
        this.table.addFixedObstacles(obstacleArray);
        sizeAfter = this.table.getFixedObstacles().size();
        Assert.assertEquals(sizeBefore+4, sizeAfter);

        Obstacle[] obstacleList = {
                new CircularObstacle(new Circle(new VectCartesian(500,0), 50),false),
                new CircularObstacle(new Circle(new VectCartesian(0,500), 50),false),
                new CircularObstacle(new Circle(new VectCartesian(500,500), 50),false),
        };
        // this.table.addFixedObstacles(obstacleList);
        sizeAfter = this.table.getFixedObstacles().size();
        Assert.assertEquals(sizeBefore+7, sizeAfter);
    }

    @Test
    public void addRectangularFixedObstacles(){
        int sizeBefore = this.table.getFixedObstacles().size();
        this.table.addFixedObstacle(new RectangularObstacle(new Rectangle(new VectCartesian(0,0), 50,50),false));
        int sizeAfter = this.table.getFixedObstacles().size();
        Assert.assertEquals(sizeBefore+1, sizeAfter);

        ArrayList<Obstacle> obstacleArray = new ArrayList<>();
        obstacleArray.add(new RectangularObstacle(new Rectangle(new VectCartesian(1000,0), 50,50),false));
        obstacleArray.add(new RectangularObstacle(new Rectangle(new VectCartesian(0,1000), 50,50),false));
        obstacleArray.add(new RectangularObstacle(new Rectangle(new VectCartesian(1000,1000), 50,50),false));
        this.table.addFixedObstacles(obstacleArray);
        sizeAfter = this.table.getFixedObstacles().size();
        Assert.assertEquals(sizeBefore+4, sizeAfter);

        Obstacle[] obstacleList = {
                new RectangularObstacle(new Rectangle(new VectCartesian(500,0), 50,50),false),
                new RectangularObstacle(new Rectangle(new VectCartesian(0,500), 50,50),false),
                new RectangularObstacle(new Rectangle(new VectCartesian(500,500), 50,50),false),
        };
        // this.table.addFixedObstacles(obstacleList);
        sizeAfter = this.table.getFixedObstacles().size();
        Assert.assertEquals(sizeBefore+7, sizeAfter);
    }

    @Test
    public void addCircularMobileObstacles(){
        int sizeBefore = this.table.getMobileObstacles().size();
        // this.table.addMobileObstacle(new CircularObstacle(new Circle(new VectCartesian(0,0),0),false));
        int sizeAfter = this.table.getMobileObstacles().size();
        Assert.assertEquals(sizeBefore+1, sizeAfter);

        ArrayList<Obstacle> obstacleArray = new ArrayList<>();
        obstacleArray.add(new CircularObstacle(new Circle(new VectCartesian(1000,0), 50),false));
        obstacleArray.add(new CircularObstacle(new Circle(new VectCartesian(0,1000), 50),false));
        obstacleArray.add(new CircularObstacle(new Circle(new VectCartesian(1000,1000), 50),false));
        // this.table.addMobileObstacles(obstacleArray);
        sizeAfter = this.table.getMobileObstacles().size();
        Assert.assertEquals(sizeBefore+4, sizeAfter);

        Obstacle[] obstacleList = {
                new CircularObstacle(new Circle(new VectCartesian(500,0), 50),false),
                new CircularObstacle(new Circle(new VectCartesian(0,500), 50),false),
                new CircularObstacle(new Circle(new VectCartesian(500,500), 50),false),
        };
        // this.table.addMobileObstacles(obstacleList);
        sizeAfter = this.table.getMobileObstacles().size();
        Assert.assertEquals(sizeBefore+7, sizeAfter);
    }

    @Test
    public void addRectangularMobileObstacles(){
        int sizeBefore = this.table.getMobileObstacles().size();
        // this.table.addMobileObstacle(new RectangularObstacle(new Rectangle(new VectCartesian(0,0), 50,50),false));
        int sizeAfter = this.table.getMobileObstacles().size();
        Assert.assertEquals(sizeBefore+1, sizeAfter);


        ArrayList<Obstacle> obstacleArray = new ArrayList<>();
        obstacleArray.add(new RectangularObstacle(new Rectangle(new VectCartesian(1000,0), 50,50),false));
        obstacleArray.add(new RectangularObstacle(new Rectangle(new VectCartesian(0,1000), 50,50),false));
        obstacleArray.add(new RectangularObstacle(new Rectangle(new VectCartesian(1000,1000), 50,50),false));
        // this.table.addMobileObstacles(obstacleArray);
        sizeAfter = this.table.getMobileObstacles().size();
        Assert.assertEquals(sizeBefore+4, sizeAfter);

        Obstacle[] obstacleList = {
                new RectangularObstacle(new Rectangle(new VectCartesian(500,0), 50,50),false),
                new RectangularObstacle(new Rectangle(new VectCartesian(0,500), 50,50),false),
                new RectangularObstacle(new Rectangle(new VectCartesian(500,500), 50,50),false),
        };
        // this.table.addMobileObstacles(obstacleList);
        sizeAfter = this.table.getMobileObstacles().size();
        Assert.assertEquals(sizeBefore+7, sizeAfter);
    }

    @Test
    public void addAlreadyExistingFixedCircularObstacle(){
        int sizeBefore = this.table.getFixedObstacles().size();
        this.table.addFixedObstacle(new CircularObstacle(new Circle(new VectCartesian(0,0),0),false));
        this.table.addFixedObstacle(new CircularObstacle(new Circle(new VectCartesian(0,0),0),false));
        int sizeAfter = this.table.getFixedObstacles().size();
        Assert.assertEquals(sizeBefore+1, sizeAfter);
    }

    @Test
    public void addAlreadyExistingFixedRectangularObstacle(){
        int sizeBefore = this.table.getFixedObstacles().size();
        this.table.addFixedObstacle(new RectangularObstacle(new Rectangle(new VectCartesian(0,0),50,50),false));
        this.table.addFixedObstacle(new RectangularObstacle(new Rectangle(new VectCartesian(0,0),50,50),false));
        int sizeAfter = this.table.getFixedObstacles().size();
        Assert.assertEquals(sizeBefore+1, sizeAfter);
    }

    @Test
    public void addAlreadyExistingMobileCircularObstacle(){
        int sizeBefore = this.table.getMobileObstacles().size();
        // this.table.addMobileObstacle(new CircularObstacle(new Circle(new VectCartesian(0,0),0),false));
        // this.table.addMobileObstacle(new CircularObstacle(new Circle(new VectCartesian(0,0),0),false));
        int sizeAfter = this.table.getMobileObstacles().size();
        Assert.assertEquals(sizeBefore+1, sizeAfter);
    }

    @Test
    public void addAlreadyExistingMobileRectangularObstacle(){
        int sizeBefore = this.table.getMobileObstacles().size();
        // this.table.addMobileObstacle(new RectangularObstacle(new Rectangle(new VectCartesian(0,0),50,50),false));
        // this.table.addMobileObstacle(new RectangularObstacle(new Rectangle(new VectCartesian(0,0),50,50),false));
        int sizeAfter = this.table.getMobileObstacles().size();
        Assert.assertEquals(sizeBefore+1, sizeAfter);
    }
}
