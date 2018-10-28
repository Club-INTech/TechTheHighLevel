package unit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import robot.OrderWrapper;
import robot.OrdersEnums.ActionsOrder;
import robot.OrdersEnums.MotionOrder;
import robot.OrdersEnums.Order;

import utils.communication.Connections;
import utils.communication.ConnectionsManager;

public class Test_OrderWrapper {

    private OrderWrapper orderWrapper;
    private ConnectionsManager connectionsManager;

    @Before
    public void setUp(){
        this.orderWrapper=new OrderWrapper();
        this.connectionsManager=new ConnectionsManager() {
            @Override
            /* On traite les messages selon leurs headers */
            protected void handleMessage(String header, String message) {
                System.out.println(header+message);
            }


        };
        this.connectionsManager.startAllConnections(Connections.TO_LOCALHOST_TEST,Connections.LOCALHOST_TEST_SERVER);
        orderWrapper.setConnection(Connections.TO_LOCALHOST_TEST);
    }

    @Test
    public void stopOrderTest(){
        orderWrapper.immobilise();
    }

    @Test
    public void sendActionOrder(){
        orderWrapper.useActuator(ActionsOrder.DesactiveLaPompe);

    }

    @Test
    public void moveLenghtwiseOrder(){
        orderWrapper.moveLenghtwise(15);
    }

    @After
    //Close connections.
    public void closeConnection() {
        Connections.TO_LOCALHOST_TEST.close();
    }

}

