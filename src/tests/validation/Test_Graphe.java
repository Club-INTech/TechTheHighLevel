package validation;

import data.Graphe;
import data.Table;
import data.graphe.Node;
import data.graphe.Ridge;
import data.table.FixedCircularObstacle;
import data.table.FixedRectangularObstacle;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.Container;
import utils.maths.Vector;

public class Test_Graphe
{
    /**
     * Graphe à tester
     */
    private Graphe graphe;
    private Container container;

    @Before
    public void setUp() throws Exception {
        container = Container.getInstance("Master");
    }

    @After
    public void tearDown() throws Exception {
        container = null;
        Container.resetInstance();
    }

    @Test
    public void testInstanciation() throws Exception {
        Table table = container.getService(Table.class);
        table.addFixedObstacle(new FixedCircularObstacle(new Vector(300, 300), 200));
        table.addFixedObstacle(new FixedRectangularObstacle(new Vector(600, 300), 300, 200));

        graphe = container.getService(Graphe.class);

        Assert.assertEquals(graphe, table.getGraphe());

        for (Node node : graphe.getNodes()) {
            Assert.assertFalse(table.isPositionInFixedObstacle(node.getPosition()));
        }

        for (Ridge ridge : graphe.getRidges()) {
            Assert.assertFalse(table.intersectAnyFixedObstacle(ridge.getSeg()));
        }
    }

    @Test
    public void testAddProvisoryNode1() throws Exception {
        graphe = container.getService(Graphe.class);
        Vector nodePos = graphe.getNodes().get(8).getPosition().clone();
        int nbNode = graphe.getNodes().size();

        Node node = graphe.addProvisoryNode(nodePos);

        Assert.assertEquals(nbNode, graphe.getNodes().size());
        Assert.assertTrue(graphe.getNodes().get(8) == node);
    }

    @Test
    public void testAddProvisoryNode2() throws Exception {
        graphe = container.getService(Graphe.class);
        Vector nodePos = graphe.getNodes().get(8).getPosition().addNewVector(new Vector(-20, 12));
        int nbNode = graphe.getNodes().size();

        Node node = graphe.addProvisoryNode(nodePos);

        Assert.assertEquals(nbNode + 1, graphe.getNodes().size());
    }

    @Test
    public void testRemoveProvisoryNode1() throws Exception {
        graphe = container.getService(Graphe.class);
        Vector nodePos = graphe.getNodes().get(8).getPosition().clone();
        int nbNode = graphe.getNodes().size();

        Node node = graphe.addProvisoryNode(nodePos);
        graphe.removeProvisoryNode(node);

        Assert.assertEquals(nbNode, graphe.getNodes().size());
    }

    @Test
    public void testRemoveProvisoryNode2() throws Exception {
        graphe = container.getService(Graphe.class);
        Vector nodePos = graphe.getNodes().get(8).getPosition().addNewVector(new Vector(-20, 12));
        int nbNode = graphe.getNodes().size();

        Node node = graphe.addProvisoryNode(nodePos);
        graphe.removeProvisoryNode(node);

        Assert.assertEquals(nbNode, graphe.getNodes().size());
    }
}
