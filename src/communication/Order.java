package communication;

import config.ConfigData;
import config.ConfigInstance;
import pfg.config.Config;

public enum Order {

    Test("test"),
    Test1("test1"),
    Test2("test2"),
    Test3("test3"),
    GoTo("goto"),
    TurnToPoint("turntopoint"),
    TurnToAngle("turntoangle"),


    ;

    private String orderStr;
    private int millisecondsToComplete;

    private Config config = ConfigInstance.getConfig();

    Order(String orderStr){
        this(orderStr, -1);
    }

    Order(String orderStr, int millisecondsToComplete){
        this.orderStr=orderStr;
        if (millisecondsToComplete>-1) {
            this.millisecondsToComplete = millisecondsToComplete;
        }
        else{
            this.millisecondsToComplete = config.getInt(ConfigData.ETHERNET_DEFAULT_DELAY);
        }
    }

    public String getOrderStr(){
        return this.orderStr;
    }
    public int getTimeToComplete(){
        return this.millisecondsToComplete;
    }
}
