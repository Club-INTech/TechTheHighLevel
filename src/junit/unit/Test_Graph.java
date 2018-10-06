package junit.unit;

import data.table.Table;
import data.table.graph.Graph;
import data.table.graph.Node;
import data.table.graph.Ridge;
import data.table.obstacle.CircularObstacle;
import data.table.obstacle.RectangularObstacle;
import utils.math.Circle;
import utils.math.Rectangle;
import utils.math.VectCartesian;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("Duplicates")
public class Test_Graph {

    private Graph graphe;
    private Table table;

    @Before
    public void before(){
        this.table=new Table();
        this.graphe=new Graph(this.table);
        this.graphe.addNode(new Node(new VectCartesian(0,0)));
        this.graphe.addNode(new Node(new VectCartesian(500,0)));
        this.graphe.addNode(new Node(new VectCartesian(1000,0)));
        this.graphe.addNode(new Node(new VectCartesian(0,500)));
        this.graphe.addNode(new Node(new VectCartesian(0,1000)));
        this.graphe.addNode(new Node(new VectCartesian(500,500)));
        this.graphe.addNode(new Node(new VectCartesian(1000,1000)));
    }

    @After
    public void after(){
        this.graphe=null;
    }

    @Test
    public void goodNumberOfRidges(){
        this.graphe.createRidges();
        Assert.assertEquals(21, this.graphe.getRidges().size());
    }

    @Test
    public void findNodeTest(){
        Node addedNode = new Node(new VectCartesian(123,123));
        this.graphe.addNode(addedNode);
        Assert.assertTrue(this.graphe.getNodes().contains(addedNode));
        Assert.assertTrue(this.graphe.getNodes().contains(new Node(new VectCartesian(500,500))));
    }

    @Test
    public void nodeRemovalTest(){
        this.graphe.createRidges();
        this.graphe.removeNode(new Node(new VectCartesian(0,0)));
        Assert.assertEquals(15, this.graphe.getRidges().size());
        Assert.assertFalse(this.graphe.getNodes().contains(new Node(new VectCartesian(0,0))));
    }

    @Test
    public void nonExistingNodeRemovalTest(){
        this.graphe.createRidges();
        this.graphe.removeNode(new Node(new VectCartesian(123,123)));
        Assert.assertEquals(21, this.graphe.getRidges().size());
    }

    @Test
    public void addAlreadyExistingNode(){
        Node addedNode = new Node(new VectCartesian(0,0));
        this.graphe.addNode(new Node(new VectCartesian(0,0)));
        this.graphe.addNode(addedNode);
        this.graphe.createRidges();
        Assert.assertEquals(21, this.graphe.getRidges().size());
    }

    @Test
    public void workingUpdateRidgesWithoutObstacles(){
        this.graphe.createRidges();
        this.graphe.updateRidges();
        boolean isOneRidgeUnusable=false;
        for (Ridge ridge : this.graphe.getRidges()){
            if (!ridge.isUsable()){
                isOneRidgeUnusable=true;
                break;
            }
        }
        Assert.assertFalse(isOneRidgeUnusable);
    }

    @Test
    public void workingUpdateRidgesWithFixedCircularObstacles(){
        this.graphe=new Graph(this.table);
        this.graphe.addNode(new Node(new VectCartesian(0,0)));
        this.graphe.addNode(new Node(new VectCartesian(1000,0)));
        this.graphe.addNode(new Node(new VectCartesian(1000,1000)));
        this.graphe.createRidges();
        this.graphe.updateRidges();
        int nbRidgesUsable=0;
        for (Ridge ridge : this.graphe.getRidges()){
            if (ridge.isUsable()){
                nbRidgesUsable++;
            }
        }
        this.table.addFixedObstacle(new CircularObstacle(new Circle(new VectCartesian(500,500),50),false));
        this.graphe.updateRidges();

        int nbRidgesUsableAfter=0;
        for (Ridge ridge : this.graphe.getRidges()){
            if (ridge.isUsable()){
                nbRidgesUsableAfter++;
            }
        }
        Assert.assertEquals(3,nbRidgesUsable);
        Assert.assertEquals(2,nbRidgesUsableAfter);
    }

    @Test
    public void workingUpdateRidgesWithFixedRectangularObstacles(){
        this.graphe=new Graph(this.table);
        this.graphe.addNode(new Node(new VectCartesian(0,0)));
        this.graphe.addNode(new Node(new VectCartesian(1000,0)));
        this.graphe.addNode(new Node(new VectCartesian(1000,1000)));
        this.graphe.createRidges();
        this.graphe.updateRidges();
        int nbRidgesUsable=0;
        for (Ridge ridge : this.graphe.getRidges()){
            if (ridge.isUsable()){
                nbRidgesUsable++;
            }
        }
        this.table.addFixedObstacle(new RectangularObstacle(new Rectangle(new VectCartesian(500,500),50,50),false));
        this.graphe.updateRidges();

        int nbRidgesUsableAfter=0;
        for (Ridge ridge : this.graphe.getRidges()){
            if (ridge.isUsable()){
                nbRidgesUsableAfter++;
            }
        }
        Assert.assertEquals(3,nbRidgesUsable);
        Assert.assertEquals(2,nbRidgesUsableAfter);
    }

    @Test
    public void workingUpdateRidgesWithMobileCircularObstacles(){
        this.graphe=new Graph(this.table);
        this.graphe.addNode(new Node(new VectCartesian(0,0)));
        this.graphe.addNode(new Node(new VectCartesian(1000,0)));
        this.graphe.addNode(new Node(new VectCartesian(1000,1000)));
        this.graphe.createRidges();
        this.graphe.updateRidges();
        int nbRidgesUsable=0;
        for (Ridge ridge : this.graphe.getRidges()){
            if (ridge.isUsable()){
                nbRidgesUsable++;
            }
        }
        this.table.addMobileObstacle(new CircularObstacle(new Circle(new VectCartesian(500,500),50),false));
        this.graphe.updateRidges();

        int nbRidgesUsableAfter=0;
        for (Ridge ridge : this.graphe.getRidges()){
            if (ridge.isUsable()){
                nbRidgesUsableAfter++;
            }
        }
        Assert.assertEquals(3,nbRidgesUsable);
        Assert.assertEquals(2,nbRidgesUsableAfter);
    }

    @Test
    public void workingUpdateRidgesWithMobileRectangularObstacles(){
        this.graphe=new Graph(this.table);
        this.graphe.addNode(new Node(new VectCartesian(0,0)));
        this.graphe.addNode(new Node(new VectCartesian(1000,0)));
        this.graphe.addNode(new Node(new VectCartesian(1000,1000)));
        this.graphe.createRidges();
        this.graphe.updateRidges();
        int nbRidgesUsable=0;
        for (Ridge ridge : this.graphe.getRidges()){
            if (ridge.isUsable()){
                nbRidgesUsable++;
            }
        }
        this.table.addMobileObstacle(new RectangularObstacle(new Rectangle(new VectCartesian(500,500),50,50),false));
        this.graphe.updateRidges();

        int nbRidgesUsableAfter=0;
        for (Ridge ridge : this.graphe.getRidges()){
            if (ridge.isUsable()){
                nbRidgesUsableAfter++;
            }
        }
        Assert.assertEquals(3,nbRidgesUsable);
        Assert.assertEquals(2,nbRidgesUsableAfter);
    }

    @Test
    public void addCircularFixedObstacle(){
        int sizeBefore = this.table.getFixedObstacles().size();
        this.table.addFixedObstacle(new CircularObstacle(new Circle(new VectCartesian(0,0),0),false));
        int sizeAfter = this.table.getFixedObstacles().size();
        Assert.assertEquals(sizeBefore+1, sizeAfter);
    }

    @Test
    public void addRectangularFixedObstacle(){
        int sizeBefore = this.table.getFixedObstacles().size();
        this.table.addFixedObstacle(new RectangularObstacle(new Rectangle(new VectCartesian(0,0), 50,50),false));
        int sizeAfter = this.table.getFixedObstacles().size();
        Assert.assertEquals(sizeBefore+1, sizeAfter);
    }

    @Test
    public void addAlreadyExistingCircularObstacle(){
        int sizeBefore = this.table.getFixedObstacles().size();
        this.table.addFixedObstacle(new CircularObstacle(new Circle(new VectCartesian(0,0),0),false));
        this.table.addFixedObstacle(new CircularObstacle(new Circle(new VectCartesian(0,0),0),false));
        int sizeAfter = this.table.getFixedObstacles().size();
        Assert.assertEquals(sizeBefore+1, sizeAfter);
    }

    @Test
    public void addAlreadyExistingRectangularObstacle(){
        int sizeBefore = this.table.getFixedObstacles().size();
        this.table.addFixedObstacle(new RectangularObstacle(new Rectangle(new VectCartesian(0,0),50,50),false));
        this.table.addFixedObstacle(new RectangularObstacle(new Rectangle(new VectCartesian(0,0),50,50),false));
        int sizeAfter = this.table.getFixedObstacles().size();
        Assert.assertEquals(sizeBefore+1, sizeAfter);
    }

    @Test
    public void createRidgesWithoutAnyNode(){
        this.graphe=new Graph(this.table);
        this.graphe.createRidges();
        Assert.assertEquals(0, this.graphe.getRidges().size());
        Assert.assertEquals(0, this.graphe.getNodes().size());
    }
}
