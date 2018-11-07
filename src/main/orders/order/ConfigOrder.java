package orders.order;

/**
 * Ordre de configuration du LL
 * @see Order
 *
 * @author rem
 */
public enum ConfigOrder implements Order {
    SET_XYO("cxyo"),
    SET_X("cx"),
    SET_Y("cy"),
    SET_ORIENTATION("co"),
    SET_POSITION("cxy"),
    SET_SPEED("ctrv"),
    SET_TRANSLATIONNAL_SPEED("ctv"),
    SET_ROTATIONNAL_SPEED("crv"),

    CONFIGURE_HOOK("nh"),
    ENABLE_HOOK("eh"),
    DISABLE_HOOK("dh")
    ;

    /**
     * Chaîne de caractère à envoyer au LL
     */
    private String stringOrder;

    /**
     * Construit un ordre de configuration
     * @param stringOrder   string correspondante
     */
    ConfigOrder(String stringOrder) {
        this.stringOrder = stringOrder;
    }

    @Override
    public String getStringOrder() {
        return stringOrder;
    }
}
