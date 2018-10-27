package data;

public enum SensorsData {

    TELEMETRE_AVANT_DROIT(new Data<Integer>(0)),
    TELEMETRE_AVANT_GAUCHE(new Data<Integer>(0)),
    TELEMETRE_COTE_DROIT(new Data<Integer>(0)),
    TELEMETRE_COTE_GAUCHE(new Data<Integer>(0)),
    TELEMETRE_ARRIERE_DROIT(new Data<Integer>(0)),
    TELEMETRE_ARRIERE_GAUCHE(new Data<Integer>(0)),

    ;
    private Data dataObject;
    SensorsData(Data dataObject){
        this.dataObject=dataObject;
    }


    /** Renvoie la valeur de la variable */
    public synchronized <T> T getData(){
        return (T)this.dataObject.getValue();
    }

    /** Affecte une valeur à la variable */
    public synchronized <T> void setData(T value){
        this.dataObject.setValue(value);
    }
}
