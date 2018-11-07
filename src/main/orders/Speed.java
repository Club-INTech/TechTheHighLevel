package orders;

/**
 * Définit les vitesses du robot à set pour le LL
 *
 * @author rem
 */
public enum Speed {
    LARGO(200, 0.5D),
    ADAGIO(400, 1.2D),
    ANDANTE(600, 2D),
    ALLEGRO(800, 3D),
    PRESTO(1000, 4D)
    ;

    /**
     * Vitesse de translation (Unité inconnu, demandez au LL ca a jamais été clair)
     */
    private int translationSpeed;

    /**
     * Vitesse de rotation
     */
    private double rotationSpeed;

    /**
     * Construit une vitesse
     * @param translationSpeed  vitesse de translation
     * @param rotationSpeed     vitesse de rotation
     */
    Speed(int translationSpeed, double rotationSpeed) {
        this.translationSpeed = translationSpeed;
        this.rotationSpeed = rotationSpeed;
    }

    /**
     * Getter
     */
    public int getTranslationSpeed() {
        return translationSpeed;
    }
    public double getRotationSpeed() {
        return rotationSpeed;
    }
}
