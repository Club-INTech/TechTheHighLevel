package validation;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pfg.config.Config;
import robot.OrderWrapper;
import robot.OrdersEnums.*;

import robot.hooks.HookNames;
import utils.ConfigData;
import utils.Container;
import utils.communication.Connections;
import utils.communication.ConnectionsManager;
import utils.container.ContainerException;
import utils.math.VectCartesian;

import java.time.LocalDateTime;
import java.util.Locale;

/**
 * Classe permettant de tester l'orderWrapper
 */
public class Test_OrderWrapper {

    /**Container*/
    private Container container;
    /**orderWrapper*/
    private OrderWrapper orderWrapper;
    /**connectionManager pour établir et fermer les connexions en local pour run les tests*/
    private ConnectionsManager connectionsManager;
    /**String ajouté pour les asserts : voir tests*/
    private static String  m;
    /**config*/
    private Config config;
    @Before
    public void setUp(){
        container=Container.getInstance("Master");
        config=container.getConfig();
        try{
            this.orderWrapper= container.getService(OrderWrapper.class);
        }
        catch (ContainerException e){
            e.printStackTrace();
        }
        this.connectionsManager=new ConnectionsManager() {

            @Override
            public void startAllConnections(Connections... connections) {
                super.startAllConnections(connections);
            }

            @Override
            protected void handleMessage(String header, String message) {
                m=header+message;
            }
        };
        this.connectionsManager.startAllConnections(Connections.TO_LOCALHOST_TEST,Connections.LOCALHOST_TEST_SERVER);
        orderWrapper.setConnection(Connections.TO_LOCALHOST_TEST);
    }

    /**
     * Test de immobilise
     * @throws Exception
     */
    @Test
    public void stopOrderTest()throws Exception{
        orderWrapper.immobilise();
        String a =MotionOrder.STOP.getOrderStr();
        Thread.sleep(1000);
        Assert.assertEquals(a,m);
    }

    /**
     * Test d'envoi des ordres
     * @throws Exception
     */
    @Test
    public void sendActionOrder() throws Exception{
        orderWrapper.useActuator(ActionsOrder.DesactiveLaPompe);
        String a =ActionsOrder.DesactiveLaPompe.getOrderStr();
        Thread.sleep(1000);
        Assert.assertEquals(a,m);

    }

    /**
     * Test du moveLenghtwise
     * @throws Exception
     */
    @Test
    public void moveLenghtwiseOrder() throws  Exception{
        orderWrapper.moveLenghtwise(15);
        String a = MotionOrder.MOVE_LENTGHWISE.getOrderStr() + " " + 15;
        Thread.sleep(1000);
        Assert.assertEquals(a,m);

    }

    /**
     * Test du turn
     * @throws Exception
     */

    @Test
    public void turnTest() throws Exception
    {
            orderWrapper.turn(Math.PI);
            String a = MotionOrder.TURN.getOrderStr() + " " + new StringBuilder(String.format(Locale.US, "%.3f", Math.PI));
            Thread.sleep(1000);
            Assert.assertEquals(a,m);
    }


    /**
     * test pour set les translations et les rotations
     * @throws Exception
     */
    @Test
    public void setTranslationnalSpeedTest() throws Exception {
        orderWrapper.setTranslationnalSpeed(2);
        String a= SpeedOrder.SET_TRANSLATION_SPEED.getOrderStr() +" "+ new StringBuilder(String.format(Locale.US,"%.3f",2.0));
        Thread.sleep(1000);
        Assert.assertEquals(a,m);
    }

    /**
     * Test pour set la vitesse de rotation
     * @throws Exception
     */
    @Test
    public void setRotationalSpeedTest() throws Exception {
        orderWrapper.setRotationnalSpeed(3);
        String a =SpeedOrder.SET_ROTATIONNAL_SPEED.getOrderStr()+" "+ new StringBuilder(String.format(Locale.US,"%.3f",3.0));
        Thread.sleep(1000);
        Assert.assertEquals(a,m);
    }

    /**
     * Test pour set la position et l'orientation
     * @throws Exception
     */
    @Test
    public void setPositionAndOrientationTest() throws Exception {
            orderWrapper.setPositionAndOrientation(new VectCartesian(2, 3), Math.PI / 2);
            String a = PositionAndOrientationOrder.SET_POSITION_AND_ORIENTATION.getOrderStr() + " " + new StringBuilder(String.format(Locale.US, "%d", 2)) + " " + new StringBuilder(String.format(Locale.US, "%d", 3)) + " " + new StringBuilder(String.format(Locale.US, "%.3f", Math.PI / 2));
            Thread.sleep(1000);
            Assert.assertEquals(a,m);

    }

    /**
     * test pour set l'orientation
     * @throws Exception
     */
    @Test
    public void setOrientationTest() throws Exception {
            orderWrapper.setOrientation(Math.PI);
            String a = PositionAndOrientationOrder.SET_ORIENTATION.getOrderStr() + " " + new StringBuilder(String.format(Locale.US, "%.3f", Math.PI));
            Thread.sleep(1000);
            Assert.assertEquals(a, m);

    }


    /**
     * test pour configurer les hooks
     * @throws Exception
     */
    @Test
    public void configureHookTest() throws Exception {
            orderWrapper.configureHook(0, new VectCartesian(2, 3), 2, Math.PI, 2, ActionsOrder.FermePorteAvant);
            String a = HooksOrder.INITIALISE_HOOK.getOrderStr() + " " + 0 + " " + new StringBuilder(String.format(Locale.US, "%d", 2)) + " " + new StringBuilder(String.format(Locale.US, "%d", 3)) + " " + new StringBuilder(String.format(Locale.US, "%d", 2)) + " " + new StringBuilder(String.format(Locale.US, "%.3f", Math.PI)) + " " + new StringBuilder(String.format(Locale.US, "%.3f", 2.0) + " " + ActionsOrder.FermePorteAvant.getOrderStr());
            Thread.sleep(1000);
            Assert.assertEquals(a,m);

    }


    /**
     *Test pour activer les hooks
     * @throws Exception
     */
    @Test
    public void enableHookTest() throws Exception {
        orderWrapper.enableHook(HookNames.SPEED_DOWN);
        String a=HooksOrder.ENABLE_HOOK.getOrderStr() +" "+ HookNames.SPEED_DOWN.getId();
        Thread.sleep(1000);
        Assert.assertEquals(a,m);
    }

    /**
     * Test pour désactiver les hooks
     * @throws Exception
     */
    @Test
    public void disableHookTest() throws Exception {
        orderWrapper.disableHook(HookNames.SPEED_DOWN);
        String a=HooksOrder.DISABLE_HOOK.getOrderStr() +" "+ HookNames.SPEED_DOWN.getId();
        Thread.sleep(1000);
        Assert.assertEquals(a,m);
    }

    /**
     * après chaque test, on ferme les connexions et réinitialise le m
     */
    @After
    public void closeConnection() {
        m="";
        Connections.TO_LOCALHOST_TEST.close();
        Connections.LOCALHOST_TEST_SERVER.close();
    }



}

