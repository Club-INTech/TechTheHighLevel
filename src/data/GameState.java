package data;

public enum GameState {

    //Exemples
    TAS_CUBE_1_PRIS(new Data<Boolean>(true)),
    TAS_CUBE_2_PRIS(new Data<Boolean>(true)),



    ;
    private Data dataObject;

    GameState(Data dataObject){
        this.dataObject=dataObject;
    }

    public <T> T getData(){
        return (T)this.dataObject.getValue();
    }

    public <T> void setData(T value){
        this.dataObject.setValue(value);
    }
}
