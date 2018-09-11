package data;

class Data<T> {

    private T value;

    Data (T value){
        this.value=value;
    }

    void setValue(T value){
        this.value=value;
    }

    T getValue(){
        return this.value;
    }
}
