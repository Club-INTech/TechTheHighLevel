package robot.OrdersEnums;

/**
 * Définition des vitesses possibles de déplacement du robot.
 * Les deux arguments passés dans les vitesses correspondent à des valeurs en mm/s pour la translation puis en rad/s pour la rotation
 */

public enum Speed implements Order
{
    //TODO régler les valeurs de vitesse en translations et rotations avec des phases de test, décider des combinaisons de vitesses inutiles

    /** Vitesse ultra lente en translation, ultra lente en rotation */
    ULTRA_SLOW_ALL(100,0.5D),

    /** Vitesse très lente en translation, très lente en rotation */
    VERY_SLOW_ALL(150,0.75D),

    /** Vitesse lente en translation, lente en rotation */
    SLOW_ALL(300, 1D),

    /** Vitesse standard de déplacement et rotation */

    MEDIUM_ALL(400,2D),

    /** Vitesse rapide en translation et rotation */
    FAST_ALL(700,2.5D),

    /** Vitesse ultra rapide en translation et rotation */
    ULTRA_FAST_ALL(1000,4D),

    /** Vitesse par défaut (utliser une des vitesses au dessus dans les parenthèses)
     *  Fait pour ne pas avoir à changer la vitesse dans tout le code
     */
    DEFAULT_SPEED(FAST_ALL);


    /** Vitesse des moteurs lors d'une translation, ce sont ces valeurs qui seront envoyées à la STM */

    public int translationSpeed;

    /** Vitesse des moteurs lors d'une rotation, ce sont ces valeurs qui seront envoyées à la STM */
    public double rotationSpeed;


    /**
     * Constructeur d'une vitesse.
     * @param translationSpeed la vitesse de translation ( en mm/s)
     * @param rotationSpeed la vitesse de rotation (en rad/s)
     */
    Speed(int translationSpeed, double rotationSpeed)
    {
        this.translationSpeed = translationSpeed;
        this.rotationSpeed = rotationSpeed;
    }

    /**
     * Constructeur pour set une vitesse par défaut
     * @param speedOrder : la vitesse qu'on veut
     */
    Speed(Speed speedOrder){
        this.translationSpeed= speedOrder.getTranslationSpeed();
        this.rotationSpeed= speedOrder.getRotationSpeed();
    }
    /**getter de la vitesse de translation*/
    public int getTranslationSpeed() {
        return translationSpeed;
    }
    /**Getter de la vitesse de rotation*/
    public double getRotationSpeed() {
        return rotationSpeed;
    }

    /**Getter de l'ordre envoyé (mais ici c'est l'orderWrapper qui fait tout) on est obligé d'override au fait */
    @Override
    public String getOrderStr() {
        return "ctv" + " " + "crv" ;
    }
}


