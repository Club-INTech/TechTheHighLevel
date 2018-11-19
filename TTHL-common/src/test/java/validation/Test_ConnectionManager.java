package validation;

import connection.Connection;
import connection.ConnectionManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.Container;

public class Test_ConnectionManager {

    private ConnectionManager connectionManager;

    private Container container;

    @Before
    public void setUp() throws Exception {
        container = Container.getInstance("Master");
        connectionManager = container.getService(ConnectionManager.class);
    }

    @After
    public void tearDown() throws Exception {
        connectionManager = null;
        container = null;
        Container.resetInstance();
    }

    @Test
    public void testInitConnections() throws Exception {
        connectionManager.initConnections(Connection.LOCALHOST_SERVER);
        Thread.sleep(20);
        connectionManager.initConnections(Connection.LOCALHOST_CLIENT);
        Thread.sleep(20);

        Assert.assertTrue(Connection.LOCALHOST_SERVER.isInitiated());
        Assert.assertTrue(Connection.LOCALHOST_CLIENT.isInitiated());
        Assert.assertTrue(connectionManager.getInitiatedConnections().contains(Connection.LOCALHOST_SERVER));
        Assert.assertTrue(connectionManager.getInitiatedConnections().contains(Connection.LOCALHOST_CLIENT));
    }

    @Test
    public void testCloseConnections() throws Exception {
        connectionManager.initConnections(Connection.LOCALHOST_SERVER);
        Thread.sleep(20);
        connectionManager.initConnections(Connection.LOCALHOST_CLIENT);
        Thread.sleep(20);

        connectionManager.closeInitiatedConnections();

        Assert.assertTrue(connectionManager.getInitiatedConnections().isEmpty());
    }
}
