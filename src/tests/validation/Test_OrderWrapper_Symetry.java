package validation;

import connection.ConnectionManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pfg.config.Config;
import robot.OrderWrapper;
import robot.OrdersEnums.ActionsOrder;
import robot.OrdersEnums.HooksOrder;
import robot.OrdersEnums.MotionOrder;
import robot.OrdersEnums.PositionAndOrientationOrder;
import utils.ConfigData;
import utils.Container;
import utils.container.ContainerException;
import utils.math.VectCartesian;

import java.util.Locale;

public class Test_OrderWrapper_Symetry {

    /**Container*/
    private Container container;
    /**orderWrapper*/
    private OrderWrapper orderWrapper;
    /**connectionManager pour établir et fermer les connexions en local pour run les tests*/
    private ConnectionManager connectionsManager;
    /**String ajouté pour les asserts : voir tests*/
    private static String  m;
    /**config*/
    private Config config;
    /**
     * Etablir ce dont on a besoin pour faire les tests
     */
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
        // TODO
    }

    /**
     * Test pour tester le turn avec la symétrie
     * @throws Exception
     */
    @Test
    public void turnTestWithSymetry() throws Exception {
            orderWrapper.turn(Math.PI / 3);
            String a = String.format(Locale.US, "%s %.3f", MotionOrder.TURN.getOrderStr(), 2 * Math.PI / 3);
            Thread.sleep(1000);
            Assert.assertEquals(a, m);

    }


    /**
     * test pour set la position et l'orientation avec la symétrie
     */
    @Test
    public void setPositionAndOrientationTestWithSymetry() throws Exception {
            orderWrapper.setPositionAndOrientation(new VectCartesian(2, 3), Math.PI / 3);
            String a = PositionAndOrientationOrder.SET_POSITION_AND_ORIENTATION.getOrderStr() + " " + new StringBuilder(String.format(Locale.US, "%d", -2)) + " " + new StringBuilder(String.format(Locale.US, "%d", 3)) + " " + new StringBuilder(String.format(Locale.US, "%.3f", 2 * Math.PI / 3));
            Thread.sleep(1000);
            Assert.assertEquals(a, m);

    }

    /**
     * Test pour configurer les hooks en symétrie
     */
    @Test
    public void configureHookTestWithSymetry() throws Exception {
            orderWrapper.configureHook(0, new VectCartesian(2, 3), 2, Math.PI / 3, 2, ActionsOrder.FermePorteDroite);
            String a = HooksOrder.INITIALISE_HOOK.getOrderStr() + " " + 0 + " " + new StringBuilder(String.format(Locale.US, "%d", -2)) + " " + new StringBuilder(String.format(Locale.US, "%d", 3)) + " " + new StringBuilder(String.format(Locale.US, "%d", 2)) + " " + new StringBuilder(String.format(Locale.US, "%.3f", 2 * Math.PI / 3)) + " " + new StringBuilder(String.format(Locale.US, "%.3f", 2.0) + " " + ActionsOrder.FermePorteGauche.getOrderStr());
            Thread.sleep(1000);
            Assert.assertEquals(a, m);

    }


    /**
     * après chaque test, on ferme les connexions, on réinitialise le m
     * et on remet la symetrie à false
     */
    @After
    public void closeConnection() {
        // TODO
    }



}
