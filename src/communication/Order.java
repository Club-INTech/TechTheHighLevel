package communication;

public enum Order {

    Test("test"),
    GoTo("goto"),
    TurnToPoint("turntopoint"),
    TurnToAngle("turntoangle"),


    ;

    private String orderStr;
    private int millisecondsToComplete;

    Order(String orderStr){
        this(orderStr, 1);
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
