package data;

public enum RobotState {

    //Exemples
    BRAS_AVANT_DEPLOYE(new Data<Boolean>(true)),
    BRAS_ARRIERE_DEPLOYE(new Data<Boolean>(true)),



    ;
    private Data dataObject;

    RobotState(Data dataObject){
        this.dataObject=dataObject;
    }

    public <T> T getData(){
        return (T)this.dataObject.getValue();
    }

    public <T> void setData(T value){
        this.dataObject.setValue(value);
    }
}
