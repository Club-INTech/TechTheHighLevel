package data;

/** Correspond à une base de données des variables à propos du robot */
public enum RobotState {

    //Exemples
    BRAS_AVANT_DEPLOYE(new Data<Boolean>(true)),
    BRAS_ARRIERE_DEPLOYE(new Data<Boolean>(true)),

    ;
    private Data dataObject;

    RobotState(Data dataObject){
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
