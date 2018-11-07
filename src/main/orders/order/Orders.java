package orders.order;

/**
 * Enum où on stocke les orders qu'on ne sait pas classer
 */
public enum Orders implements Order {

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

    Montlhery("montlhery"),
    Avance("av"),
    Recule("rc"),
    Left("tg"),
    Right("td"),
    Stop("sstop"),
        ;

    /**Ordre envoyé au LL*/
    private String orderStr;

    /**Durée de l'action en ms*/
    private int actionDuration;

    /**
     * Constructeur qui ne précise pas la durée de l'action
     * @param orderStr : order envoyé au LL
     */
    Orders(String orderStr){
        this(orderStr, 0);
    }

    /**
     * Constructeur qui précise la durée de l'action
     * @param orderStr : ordre envoyé au LL
     * @param actionDuration : durée de l'action
     */

    Orders(String orderStr, int actionDuration){
        this.orderStr=orderStr;
        this.actionDuration=actionDuration;
    }

    /**
     * Getter de l'ordre
     * @return l'ordre en string envoyé
     */
    public String getOrderStr(){
        return this.orderStr;
    }

    /**
     * Getter de la durée de l'action
     * @return durée de l'action
     */
    public int getActionDuration(){
        return this.actionDuration;
    }

}
