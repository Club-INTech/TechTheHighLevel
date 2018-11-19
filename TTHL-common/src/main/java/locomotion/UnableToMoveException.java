package locomotion;

import data.XYO;

/**
 * Exceptions de locomotion
 */
public class UnableToMoveException extends Exception {

    /**
     * Point & Orientation visé par le robot
     */
    private XYO aim;

    /**
     * Raison du blocage
     */
    private UnableToMoveReason reason;

    /**
     * @param aim
     *              position et orientation visées par le robot
     * @param reason
     *              raison de blocage
     */
    public UnableToMoveException(XYO aim, UnableToMoveReason reason) {
        this.aim = aim;
        this.reason = reason;
    }

    /**
     * @param s
     *              Message d'exception
     * @param aim
     *              position et orientation visées par le robot
     * @param reason
     *              raison de blocage
     */
    public UnableToMoveException(String s, XYO aim, UnableToMoveReason reason) {
        super(s);
        this.aim = aim;
        this.reason = reason;
    }

    /**
     * Getters & Setters
     */
    public XYO getAim() {
        return aim;
    }
    public UnableToMoveReason getReason() {
        return reason;
    }
}
