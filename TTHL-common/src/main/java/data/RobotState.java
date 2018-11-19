package data;

/** Correspond à une base de données des variables à propos du main.robot */
public enum RobotState {

    //Exemples
    BRAS_AVANT_DEPLOYE(true),
    BRAS_ARRIERE_DEPLOYE(true),

    ;
    private Object valueObject;
    RobotState(Object valueObject){
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
