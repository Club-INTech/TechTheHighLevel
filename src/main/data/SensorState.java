package data;

/**
 * Liste les données du robot et leurs état
 */
public enum SensorState {
    EXEMPLE(1.5, Double.class),
    CUBE_PRIS(0, Integer.class)
    ;

    /**
     * La donnée
     */
    private Object data;

    /**
     * La classe de la donnée : évite un transtypage après instanciation
     */
    private Class c;

    /**
     * Construit un SensorState
     */
    SensorState(Object object, Class<?> c) {
        this.data = object;
        this.c = c;
    }

    /**
     * Renvoie la valeur
     */
    public synchronized Object getData() {
        return this.data;
    }

    /**
     * Set la valeur : package-private pour éviter qu'autre chose que le controller écrit les données
     * @param object    valeur à écrire
     * @throws ClassCastException   si l'on essai d'assigner à un paramètre un objet d'une autre classe
     */
    synchronized void setData(Object object) {
        if (object.getClass() != this.c) {
            throw new ClassCastException("Cette donnée est de type " + this.c + ", trouvé : " + object.getClass());
        }
        this.data = object;
    }
}
