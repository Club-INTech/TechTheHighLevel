package embedded;

import connection.Connection;
import data.controlers.Listener;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.Container;

public class Test_Listener {

    private Container container;

    private Listener listener;

    @Before
    public void setUp() throws Exception {
        container = Container.getInstance("Master");
        listener = container.getService(Listener.class);
    }

    @After
    public void tearDown() {
        listener = null;
        container = null;
        Container.resetInstance();
    }

    @Test
    public void testInitialisation() throws Exception {
        container.startInstanciedThreads();
        Thread.sleep(5000);
        // TODO : lancer le processus de gestion du Lidar !

        Assert.assertTrue(Connection.LIDAR.isInitiated());
        Assert.assertTrue(Connection.TEENSY_MASTER.isInitiated());
        Assert.assertTrue(Connection.SLAVE.isInitiated());
    }
}
