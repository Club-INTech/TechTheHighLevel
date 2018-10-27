package unit;

import data.SensorsData;
import data.controller.SensorsHandler;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Test_SensorsHandler {

    private SensorsHandler sensorsHandler;

    @Before
    public void before(){
        this.sensorsHandler = new SensorsHandler();
    }

    @After
    public void after(){

    }

    @Test
    public void testSetGetData(){
        this.sensorsHandler.parseMessage("IR1 5");
        Assert.assertEquals(5, SensorsData.TELEMETRE_AVANT_GAUCHE.getData());
    }

}
