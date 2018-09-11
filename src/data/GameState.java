package data;

/** Correspond à une base de données des variables à propos de tout sauf le robot */
public enum GameState {

    //Exemples
    TAS_CUBE_1_PRIS(new Data<Boolean>(true)),
    DISTANCE_ENNEMY(new Data<Integer>(160)),



    ;
    private Data dataObject;

    GameState(Data dataValue){
        this.dataObject=dataValue;
    }

    /** Renvoie la valeur de la variable */
    public <T> T getData(){ return (T)this.dataObject.getValue(); }

    /** Affecte une valeur à la variable */
    public <T> void setData(T value){
        this.dataObject.setValue(value);
    }
}
