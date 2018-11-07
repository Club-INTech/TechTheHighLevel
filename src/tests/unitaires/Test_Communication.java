package unitaires;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.communication.CommunicationException;
import utils.communication.SocketClientInterface;
import utils.communication.SocketServerInterface;

import java.util.Optional;

public class Test_Communication {

    public SocketServerInterface interface1;

    public SocketClientInterface interface2;

    @Before
    public void setUp() throws Exception {
        interface1 = new SocketServerInterface("localhost", 10200, false);
        interface1.init();
    }

    @After
    public void tearDown() throws Exception {
        interface1.close();
        interface2.close();
        interface1 = null;
        interface2 = null;
        System.gc();
    }

    @Test
    public void testBidirectionnal() throws Exception {
        interface2 = new SocketClientInterface("localhost", 10200, false);
        interface2.init();

        interface1.send("M1");
        interface1.send("M2");
        interface2.send("R1");
        interface1.send("M3");

        Thread.sleep(100);
        Optional<String> m1 = interface2.read();
        Optional<String> m2 = interface2.read();
        Optional<String> m3 = interface2.read();
        Optional<String> m4 = interface2.read();

        Optional<String> r1 = interface1.read();
        Optional<String> r2 = interface1.read();

        Assert.assertTrue(m1.isPresent());
        Assert.assertTrue(m2.isPresent());
        Assert.assertTrue(m3.isPresent());
        Assert.assertFalse(m4.isPresent());
        Assert.assertTrue(r1.isPresent());
        Assert.assertFalse(r2.isPresent());

        Assert.assertEquals("M1", m1.get());
        Assert.assertEquals("M2", m2.get());
        Assert.assertEquals("M3", m3.get());
        Assert.assertEquals("R1", r1.get());
    }

    @Test(expected = CommunicationException.class)
    public void testUnidirectionnal() throws Exception {
        interface2 = new SocketClientInterface("localhost", 10200, true);
        interface2.init();

        interface1.send("AH");

        Thread.sleep(100);
        Optional<String> m1 = interface2.read();

        Assert.assertTrue(m1.isPresent());

        interface2.send("HE");
    }
}
