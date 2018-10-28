package robot.OrdersEnums;

/**
 * Enum qui contient tous les ordres concernant la position et l'orientation du robot
 */
public enum PositionAndOrientationOrder implements Order {

    CXYO("cxyo"),
    XYO("?xyo"),
    SET_POSITION_AND_ORIENTATION("cxyo"),
    SET_ORIENTATION("co"),

    ;

    /**Ordre envoyé au LL*/
    private String orderStr;

    /**Durée de l'action en ms*/
    private int actionDuration;

    /**
     * Constructeur qui ne précise pas la durée de l'action
     * @param orderStr : order envoyé au LL
     */
    PositionAndOrientationOrder(String orderStr){
        this(orderStr, 0);
    }

    /**
     * Constructeur qui précise la durée de l'action
     * @param orderStr : ordre envoyé au LL
     * @param actionDuration : durée de l'action
     */

    PositionAndOrientationOrder(String orderStr, int actionDuration){
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
