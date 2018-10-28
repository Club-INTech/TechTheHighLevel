package robot.OrdersEnums;

/**
 * Enum qui contient tous les ordres envoyés au LL concernant les mouvements du robot
 */
public enum MotionOrder implements Order {
    /**Avancer*/
    MOVE_LENTGHWISE("d"),
    /**Tourner*/
    TURN("t"),
    /**S'arrêter*/
    STOP("stop"),
    ;

    /**Ordre envoyé au LL*/
    private String orderStr;

    /**Durée de l'action en ms*/
    private int actionDuration;

    /**
     * Constructeur qui ne précise pas la durée de l'action
     * @param orderStr : order envoyé au LL
     */
    MotionOrder(String orderStr){
        this(orderStr, 0);
    }

    /**
     * Constructeur qui précise la durée de l'action
     * @param orderStr : ordre envoyé au LL
     * @param actionDuration : durée de l'action
     */

    MotionOrder(String orderStr, int actionDuration){
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
