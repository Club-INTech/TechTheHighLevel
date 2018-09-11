package communication;

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
