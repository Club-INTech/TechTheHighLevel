package utils.communication;

import utils.ConfigData;
import utils.ConfigInstance;
import pfg.config.Config;

public enum Order {

    Test("test"),
    Test1("test1"),
    Test2("test2"),
    Test3("test3"),
    GoTo("goto"),
    TurnToPoint("turntopoint"),
    TurnToAngle("turntoangle"),
    Ping("?"),
    NewHook("nh"),
    CXYO("cxyo"),
    XYO("?xyo"),


    Montlhery("montlhery"),
    Avance("av"),
    Recule("rc"),
    Stop("sstop"),

    ;

    private String orderStr;
    private int millisecondsToComplete;

    private Config config = ConfigInstance.getConfig();

    Order(String orderStr){
        this(orderStr, -1);
    }

    Order(String orderStr, int millisecondsToComplete){
        this.orderStr=orderStr;
        if (millisecondsToComplete>config.getInt(ConfigData.ETHERNET_DEFAULT_TIME)) {
            this.millisecondsToComplete = millisecondsToComplete;
        }
        else{
            this.millisecondsToComplete = config.getInt(ConfigData.ETHERNET_DEFAULT_TIME);
        }
    }

    public String getOrderStr(){
        return this.orderStr;
    }
    public int getTimeToComplete(){
        return this.millisecondsToComplete;
    }
}
