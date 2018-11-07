package validation;

import connection.Connection;
import connection.ConnectionManager;
import orders.OrderWrapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.ConfigData;
import utils.Container;
import utils.maths.Vector;

import java.util.Optional;

public class Test_OrderWrapper {

    private ConnectionManager connectionManager;

    private OrderWrapper orderWrapper;

    private Container container;

    @Before
    public void setUp() throws Exception {
        container = Container.getInstance("Master");
        orderWrapper = container.getService(OrderWrapper.class);
        connectionManager = container.getService(ConnectionManager.class);
        connectionManager.initConnections(Connection.LOCALHOST_SERVER);
        Thread.sleep(50);
        connectionManager.initConnections(Connection.LOCALHOST_CLIENT);
        orderWrapper.setLowLevelConnection(Connection.LOCALHOST_SERVER);
    }

    @After
    public void tearDown() throws Exception {
        connectionManager = null;
        container = null;
        orderWrapper = null;
        Container.resetInstance();
    }

    @Test
    public void testMoveLengthwise() throws Exception {
        orderWrapper.moveLengthwise(58);
        Thread.sleep(20);
        Optional<String> message = Connection.LOCALHOST_CLIENT.read();

        Assert.assertTrue(message.isPresent());
        Assert.assertEquals("d 58", message.get());
    }

    @Test
    public void testTurn() throws Exception {
        orderWrapper.turn(1.21);
        Thread.sleep(20);
        Optional<String> message = Connection.LOCALHOST_CLIENT.read();

        Assert.assertTrue(message.isPresent());
        Assert.assertEquals("t 1.210", message.get());
    }

    @Test
    public void testMoveToPoint() throws Exception {
        orderWrapper.moveToPoint(new Vector(52, 67));
        Thread.sleep(20);
        Optional<String> message = Connection.LOCALHOST_CLIENT.read();

        Assert.assertTrue(message.isPresent());
        Assert.assertEquals("p 52 67", message.get());
    }

    @Test
    public void testTurnSymetrie() throws Exception {
        container.getConfig().override(ConfigData.COULEUR, "jaune");
        orderWrapper.updateConfig(container.getConfig());
        orderWrapper.setLowLevelConnection(Connection.LOCALHOST_SERVER);

        orderWrapper.turn(3*Math.PI/5);
        Thread.sleep(20);
        Optional<String> message = Connection.LOCALHOST_CLIENT.read();

        Assert.assertTrue(message.isPresent());
        Assert.assertEquals("t 1.257", message.get());
    }

    @Test
    public void testMoveToPointSymetrie() throws Exception {
        container.getConfig().override(ConfigData.COULEUR, "jaune");
        orderWrapper.updateConfig(container.getConfig());
        orderWrapper.setLowLevelConnection(Connection.LOCALHOST_SERVER);

        orderWrapper.moveToPoint(new Vector(97, 124));
        Thread.sleep(20);
        Optional<String> message = Connection.LOCALHOST_CLIENT.read();

        Assert.assertTrue(message.isPresent());
        Assert.assertEquals("p -97 124", message.get());
    }
}
