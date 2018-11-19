package orders.order;


/**
 * Enum qui contient tous les ordres concernant la vitesse
 */
public enum SpeedOrder implements Order {

    SET_TRANSLATION_SPEED("ctv"),
    SET_ROTATIONNAL_SPEED("crv"),
    SET_SPEED("ctrv"),
    ;
    /**Ordre envoyé au LL*/
    private String orderStr;

    /**Durée de l'action en ms*/
    private int actionDuration;

    /**
     * Constructeur qui ne précise pas la durée l'action
     * @param orderStr action à faire
     */
    SpeedOrder(String orderStr){
        this(orderStr, 0);
    }

    /**
     * Constructeur qui précise l'action et sa durée
     * @param orderStr : action à faire
     * @param actionDuration : durée de l'action
     */

    SpeedOrder(String orderStr, int actionDuration){
        this.orderStr=orderStr;
        this.actionDuration=actionDuration;
    }
    /**Getter de l'ordrer envoyé au LL
     * @return ordre en string
     * */
    public String getOrderStr(){
        return this.orderStr;
    }
    /**Getter de la durée de l'action
     * @return durée de l'action
     * */
    public int getActionDuration(){
        return this.actionDuration;
    }
}
