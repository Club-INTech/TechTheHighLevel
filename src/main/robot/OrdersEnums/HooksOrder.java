package robot.OrdersEnums;

/**
 * Enum qui contient tous les ordres envoyé au LL qui concernent les hooks
 */
public enum HooksOrder implements Order {
    /**Initialiser un hook , c'est dire au LL qu'on veut qu'il y'ait tel hook à tel endroit **/
    INITIALISE_HOOK("nh"),
    /**Permet d'activer un hook, c'est pas parce qu'un hook est configuré qu'il sera activé, peut être on veut l'utiliser une seule fois  */
    ENABLE_HOOK("eh"),
    /**Permet de désactiver un hool*/
    DISABLE_HOOK("dh"),
        ;

    /**Ordre envoyé au LL*/
    private String orderStr;

    /**Durée de l'action en ms*/
    private int actionDuration;

    /**
     * Constructeur qui ne précise pas la durée des actions
     * @param orderStr ordre envoyé au LL
     */
    HooksOrder(String orderStr){
        this(orderStr, 0);
    }

    /**
     * Constructeur qui précise la durée des actions
     * @param orderStr
     * @param actionDuration
     */
    HooksOrder(String orderStr, int actionDuration){
        this.orderStr=orderStr;
        this.actionDuration=actionDuration;
    }
    /**getter de l'ordre
     * @return ordre en string envoyé
     * */
    public String getOrderStr(){
        return this.orderStr;
    }
    /**Getter de la durée de l'action
     *@return durée de l'action
     * */
    public int getActionDuration(){
        return this.actionDuration;
    }

}
