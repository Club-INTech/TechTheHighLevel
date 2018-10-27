package data;

public enum SensorsData {

    TELEMETRE_AVANT_GAUCHE(0),
    TELEMETRE_AVANT_DROIT(0),
    TELEMETRE_ARRIERE_GAUCHE(0),
    TELEMETRE_ARRIERE_DROIT(0),
    TELEMETRE_COTE_GAUCHE(0),
    TELEMETRE_COTE_DROIT(0),

    ;
    private Object valueObject;
    SensorsData(Object valueObject){
        this.valueObject=valueObject;
    }

    /** Renvoie la valeur de la variable */
    public synchronized Object getData(){
        return this.valueObject;
    }

    /** Affecte une valeur à la variable */
    public synchronized void setData(Object value){
        this.valueObject=value;
    }
}
