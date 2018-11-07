package validation;

import data.RobotState;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Test_RobotState {

    @Before
    public void before(){
    }

    @After
    public void after(){

    }

    @Test
    public void testSetGetData(){
        RobotState.BRAS_ARRIERE_DEPLOYE.setData(false);
        Assert.assertEquals(false, RobotState.BRAS_ARRIERE_DEPLOYE.getData());
        RobotState.BRAS_ARRIERE_DEPLOYE.setData(true);
        Assert.assertEquals(true, RobotState.BRAS_ARRIERE_DEPLOYE.getData());
    }
}
