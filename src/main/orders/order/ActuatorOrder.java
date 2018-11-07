package orders.order;

/**
 * Définit les ordres d'actionneurs
 * @see ActuatorOrder
 *
 * @author rem
 */
public enum ActuatorOrder implements Order {
    ;

    /**
     * Chaîne de caractère à envoyer au LL
     */
    private String stringOrder;

    /**
     * Construit un ordre actionneur
     * @param stringOrder   string correspondante
     */
    ActuatorOrder(String stringOrder) {
        this.stringOrder = stringOrder;
    }

    @Override
    public String getStringOrder() {
        return stringOrder;
    }
}
