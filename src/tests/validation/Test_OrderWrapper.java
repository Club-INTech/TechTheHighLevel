package validation;

import connection.Connection;
import connection.ConnectionManager;
import orders.OrderWrapper;
import orders.hooks.HookNames;
import orders.order.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pfg.config.Config;
import utils.Container;
import utils.math.VectCartesian;

import java.util.Locale;
import java.util.Optional;

/**
 * Classe permettant de tester l'orderWrapper
 */
public class Test_OrderWrapper {

    /**Container*/
    private Container container;
    /**orderWrapper*/
    private OrderWrapper orderWrapper;
    /**connectionManager pour établir et fermer les connexions en local pour run les tests*/
    private ConnectionManager connectionsManager;
    /**String ajouté pour les asserts : voir tests*/
    private Optional<String> m;
    /**config*/
    private Config config;
    @Before
    public void setUp() throws Exception{
        container = Container.getInstance("Master");
        config = container.getConfig();
        orderWrapper=container.getService(OrderWrapper.class);
        connectionsManager=container.getService(ConnectionManager.class);
        connectionsManager.initConnections(Connection.LOCALHOST_SERVER);
        Thread.sleep(50);
        connectionsManager.initConnections(Connection.LOCALHOST_CLIENT);
        orderWrapper.setConnection(Connection.LOCALHOST_CLIENT);

    }

    /**
     * Test de immobilise
     * @throws Exception
     */
    @Test
    public void stopOrderTest()throws Exception{
        orderWrapper.immobilise();
        Thread.sleep(10);
        m=Connection.LOCALHOST_SERVER.read();
        Assert.assertTrue(m.isPresent());
        String a = MotionOrder.STOP.getOrderStr();
        Assert.assertEquals(a,m.get());
    }

    /**
     * Test d'envoi des ordres
     * @throws Exception
     */
    @Test
    public void sendActionOrder() throws Exception{
        orderWrapper.useActuator(ActionsOrder.DesactiveLaPompe);
        Thread.sleep(10);
        m=Connection.LOCALHOST_SERVER.read();
        Assert.assertTrue(m.isPresent());
        String a =ActionsOrder.DesactiveLaPompe.getOrderStr();
        Assert.assertEquals(a,m.get());

    }

    /**
     * Test du moveLenghtwise
     * @throws Exception
     */
    @Test
    public void moveLenghtwiseOrder() throws  Exception{
        orderWrapper.moveLenghtwise(15);
        Thread.sleep(10);
        m=Connection.LOCALHOST_SERVER.read();
        Assert.assertTrue(m.isPresent());
        String a = MotionOrder.MOVE_LENTGHWISE.getOrderStr() + " " + 15;
        Assert.assertEquals(a, m.get());

    }

    /**
     * Test du turn
     * @throws Exception
     */

    @Test
    public void turnTest() throws Exception
    {
        orderWrapper.turn(Math.PI);
        Thread.sleep(10);
        m=Connection.LOCALHOST_SERVER.read();
        Assert.assertTrue(m.isPresent());
        String a = MotionOrder.TURN.getOrderStr() + " " + new StringBuilder(String.format(Locale.US, "%.3f", Math.PI));
        Assert.assertEquals(a,m.get());
    }


    /**
     * test pour set les translations et les rotations
     * @throws Exception
     */
    @Test
    public void setTranslationnalSpeedTest() throws Exception {
        orderWrapper.setTranslationnalSpeed(2);
        Thread.sleep(10);
        m=Connection.LOCALHOST_SERVER.read();
        Assert.assertTrue(m.isPresent());
        String a= SpeedOrder.SET_TRANSLATION_SPEED.getOrderStr() +" "+ new StringBuilder(String.format(Locale.US,"%.3f",2.0));
        Assert.assertEquals(a,m.get());
    }

    /**
     * Test pour set la vitesse de rotation
     * @throws Exception
     */
    @Test
    public void setRotationalSpeedTest() throws Exception {
        orderWrapper.setRotationnalSpeed(3);
        Thread.sleep(10);
        m=Connection.LOCALHOST_SERVER.read();
        Assert.assertTrue(m.isPresent());
        String a =SpeedOrder.SET_ROTATIONNAL_SPEED.getOrderStr()+" "+ new StringBuilder(String.format(Locale.US,"%.3f",3.0));
        Assert.assertEquals(a,m.get());
    }

    /**
     * Test pour set la position et l'orientation
     * @throws Exception
     */
    @Test
    public void setPositionAndOrientationTest() throws Exception {
        orderWrapper.setPositionAndOrientation(new VectCartesian(2, 3), Math.PI / 2);
        Thread.sleep(10);
        m=Connection.LOCALHOST_SERVER.read();
        Assert.assertTrue(m.isPresent());
        String a = PositionAndOrientationOrder.SET_POSITION_AND_ORIENTATION.getOrderStr() + " " + new StringBuilder(String.format(Locale.US, "%d", 2)) + " " + new StringBuilder(String.format(Locale.US, "%d", 3)) + " " + new StringBuilder(String.format(Locale.US, "%.3f", Math.PI / 2));
        Assert.assertEquals(a,m.get());
    }

    /**
     * test pour set l'orientation
     * @throws Exception
     */
    @Test
    public void setOrientationTest() throws Exception {
        orderWrapper.setOrientation(Math.PI);
        Thread.sleep(10);
        m=Connection.LOCALHOST_SERVER.read();
        Assert.assertTrue(m.isPresent());
        String a = PositionAndOrientationOrder.SET_ORIENTATION.getOrderStr() + " " + new StringBuilder(String.format(Locale.US, "%.3f", Math.PI));
        Assert.assertEquals(a, m.get());
    }

    /**
     * test pour configurer les hooks
     * @throws Exception
     */
    @Test
    public void configureHookTest() throws Exception {
        orderWrapper.configureHook(0, new VectCartesian(2, 3), 2, Math.PI, 2, ActionsOrder.FermePorteAvant);
        Thread.sleep(10);
        m=Connection.LOCALHOST_SERVER.read();
        Assert.assertTrue(m.isPresent());
        String a = HooksOrder.INITIALISE_HOOK.getOrderStr() + " " + 0 + " " + new StringBuilder(String.format(Locale.US, "%d", 2)) + " " + new StringBuilder(String.format(Locale.US, "%d", 3)) + " " + new StringBuilder(String.format(Locale.US, "%d", 2)) + " " + new StringBuilder(String.format(Locale.US, "%.3f", Math.PI)) + " " + new StringBuilder(String.format(Locale.US, "%.3f", 2.0) + " " + ActionsOrder.FermePorteAvant.getOrderStr());
        Assert.assertEquals(a,m.get());
    }

    /**
     *Test pour activer les hooks
     * @throws Exception
     */
    @Test
    public void enableHookTest() throws Exception {
        orderWrapper.enableHook(HookNames.SPEED_DOWN);
        Thread.sleep(10);
        m=Connection.LOCALHOST_SERVER.read();
        Assert.assertTrue(m.isPresent());
        String a=HooksOrder.ENABLE_HOOK.getOrderStr() +" "+ HookNames.SPEED_DOWN.getId();
        Assert.assertEquals(a,m.get());
    }

    /**
     * Test pour désactiver les hooks
     * @throws Exception
     */
    @Test
    public void disableHookTest() throws Exception {
        orderWrapper.disableHook(HookNames.SPEED_DOWN);
        Thread.sleep(10);
        m=Connection.LOCALHOST_SERVER.read();
        Assert.assertTrue(m.isPresent());
        String a = HooksOrder.DISABLE_HOOK.getOrderStr() +" "+ HookNames.SPEED_DOWN.getId();
        Assert.assertEquals(a,m.get());
    }

    /**
     * après chaque test, on ferme les connexions et réinitialise le m
     */
    @After
    public void closeConnection() throws Exception{
        container = null;
        config = null;
        orderWrapper=null;
        connectionsManager.closeInitiatedConnections();
        connectionsManager=null;
        Container.resetInstance();
    }
}

