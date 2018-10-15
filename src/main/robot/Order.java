package robot;

/** Contient la base de donnée des ordres du main.robot. */

public enum Order {

    Test("tests"),
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
    OuvrePorteAvant("olpAv"),
    FermePorteAvant("flpAv"),
    OuvrePorteArriere("olpAr"),
    FermePorteArriere("flpAr"),
    ActiveLaPompe("alp"),
    DesactiveLaPompe("dlp"),


    Montlhery("montlhery"),
    Avance("av"),
    Recule("rc"),
    Left("tg"),
    Right("td"),
    Stop("sstop"),

    ;

    private String orderStr;
    private int millisecondsToComplete;

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
