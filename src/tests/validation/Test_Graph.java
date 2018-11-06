package validation;

import data.Table;
import data.table.Graph;
import data.table.graph.Node;
import data.table.graph.Ridge;
import utils.Container;
import utils.container.ContainerException;
import utils.math.VectCartesian;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

@SuppressWarnings("Duplicates")
public class Test_Graph {

    private Graph graph;

    private Table table;

    private Container container;

    @Before
    public void setUp() throws ContainerException {
        container = Container.getInstance("Master");
        this.table = container.getService(Table.class);
        this.graph = new Graph(this.table);
        this.graph.addNode(new Node(new VectCartesian(0,0)));
        this.graph.addNode(new Node(new VectCartesian(500,0)));
        this.graph.addNode(new Node(new VectCartesian(1000,0)));
        this.graph.addNode(new Node(new VectCartesian(0,500)));
        this.graph.addNode(new Node(new VectCartesian(0,1000)));
        this.graph.addNode(new Node(new VectCartesian(500,500)));
        this.graph.addNode(new Node(new VectCartesian(1000,1000)));
    }

    @After
    public void tearDown(){
        this.graph =null;
    }

    @Test
    public void goodNumberOfRidges() throws Exception {
        this.graph.createRidges();
        Assert.assertEquals(21, this.graph.getRidges().size());
    }

    @Test
    public void findNodeTest() throws Exception {
        Node addedNode = new Node(new VectCartesian(123,123));
        this.graph.addNode(addedNode);
        Assert.assertTrue(this.graph.getNodes().contains(addedNode));
        Assert.assertTrue(this.graph.getNodes().contains(new Node(new VectCartesian(500,500))));
    }

    @Test
    public void nodeRemovalTest() throws Exception {
        this.graph.createRidges();
        Node nodeToRemove = new Node(new VectCartesian(0,0));
        this.graph.removeNode(nodeToRemove);

        boolean removedNodeExistsAsNeighbour = false;
        for (Node node : graph.getNodes()){
            for (Map.Entry<Ridge, Node> entry : node.getNeighboursCopy().entrySet()){
                if (entry.getValue().getNeighboursCopy().containsValue(nodeToRemove)){
                    removedNodeExistsAsNeighbour=true;
                    break;
                }
            }
        }

        Assert.assertFalse(removedNodeExistsAsNeighbour);
        Assert.assertEquals(15, this.graph.getRidges().size());
        Assert.assertFalse(this.graph.getNodes().contains(new Node(new VectCartesian(0,0))));
    }

    @Test
    public void nonExistingNodeRemovalTest() throws Exception {
        this.graph.createRidges();
        this.graph.removeNode(new Node(new VectCartesian(123,123)));
        Assert.assertEquals(21, this.graph.getRidges().size());
    }

    @Test
    public void addAlreadyExistingNode() throws Exception {
        Node addedNode = new Node(new VectCartesian(0,0));
        this.graph.addNode(new Node(new VectCartesian(0,0)));
        this.graph.addNode(addedNode);
        this.graph.createRidges();
        Assert.assertEquals(21, this.graph.getRidges().size());
    }

    @Test
    public void workingUpdateRidgesWithoutObstacles() throws Exception {
        this.graph.createRidges();
        this.graph.updateRidges();
        boolean isOneRidgeUnusable=false;
        for (Ridge ridge : this.graph.getRidges()){
            if (!ridge.isUsable()){
                isOneRidgeUnusable=true;
                break;
            }
        }
        Assert.assertFalse(isOneRidgeUnusable);
    }

    @Test
    public void workingUpdateRidgesWithMobileCircularObstacles() throws Exception {
        this.graph =new Graph(this.table);
        this.graph.addNode(new Node(new VectCartesian(0,0)));
        this.graph.addNode(new Node(new VectCartesian(1000,0)));
        this.graph.addNode(new Node(new VectCartesian(1000,1000)));
        this.graph.createRidges();
        this.graph.updateRidges();
        int nbRidgesUsable=0;
        for (Ridge ridge : this.graph.getRidges()){
            if (ridge.isUsable()){
                nbRidgesUsable++;
            }
        }
        // this.table.addMobileObstacle(new CircularObstacle(new Circle(new VectCartesian(500,500),50),false));
        this.graph.updateRidges();

        int nbRidgesUsableAfter=0;
        for (Ridge ridge : this.graph.getRidges()){
            if (ridge.isUsable()){
                nbRidgesUsableAfter++;
            }
        }
        Assert.assertEquals(3,nbRidgesUsable);
        Assert.assertEquals(2,nbRidgesUsableAfter);
    }

    @Test
    public void workingUpdateRidgesWithMobileRectangularObstacles() throws Exception {
        this.graph = new Graph(this.table);
        this.graph.addNode(new Node(new VectCartesian(0,0)));
        this.graph.addNode(new Node(new VectCartesian(1000,0)));
        this.graph.addNode(new Node(new VectCartesian(1000,1000)));
        this.graph.createRidges();
        this.graph.updateRidges();
        int nbRidgesUsable=0;
        for (Ridge ridge : this.graph.getRidges()){
            if (ridge.isUsable()){
                nbRidgesUsable++;
            }
        }
        // this.table.addMobileObstacle(new RectangularObstacle(new Rectangle(new VectCartesian(500,500),50,50),false));
        this.graph.updateRidges();

        int nbRidgesUsableAfter=0;
        for (Ridge ridge : this.graph.getRidges()){
            if (ridge.isUsable()){
                nbRidgesUsableAfter++;
            }
        }
        Assert.assertEquals(3,nbRidgesUsable);
        Assert.assertEquals(2,nbRidgesUsableAfter);
    }

    @Test
    public void createRidgesWithoutAnyNode() throws Exception {
        this.graph =new Graph(this.table);
        this.graph.createRidges();
        Assert.assertEquals(0, this.graph.getRidges().size());
        Assert.assertEquals(0, this.graph.getNodes().size());
    }
}
