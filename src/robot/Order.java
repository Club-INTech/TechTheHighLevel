package robot;

import pfg.config.Config;
import utils.ConfigData;
import utils.ConfigInstance;

/** Contient la base de donnée des ordres du robot. */

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
        this(orderStr, 0);
    }

    Order(String orderStr, int millisecondsToComplete){
        this.orderStr=orderStr;
        this.millisecondsToComplete=millisecondsToComplete;
    }

    public String getOrderStr(){
        return this.orderStr;
    }
    public int getTimeToComplete(){
        return this.millisecondsToComplete;
    }
}
