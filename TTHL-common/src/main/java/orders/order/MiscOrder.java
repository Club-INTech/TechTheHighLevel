package orders.order;

/**
 * Ordres divers (inclassables)
 *
 * @author rem
 */
public enum MiscOrder {

    TEST("tests"),
    PING("?"),
    ;
    /**
     * Ordre envoyé au LL
     */
    private String orderStr;

    /**
     * Constructeur qui ne précise pas la durée de l'action
     * @param orderStr : order envoyé au LL
     */
    MiscOrder(String orderStr){
        this.orderStr = orderStr;
    }

    /**
     * Getter de l'ordre
     * @return l'ordre en string envoyé
     */
    public String getOrderStr(){
        return this.orderStr;
    }

}
