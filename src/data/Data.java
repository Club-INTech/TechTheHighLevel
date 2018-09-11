package data;

class Data<T> {

    /** Valeur stockée dans l'objet*/
    private T value;

    Data (T value){
        this.value=value;
    }

    /** Permet de définir la valeur stockée dans cet objet */
    void setValue(T value){
        this.value=value;
    }

    /** Permet de récupérer la valeur stockée dans cet objet */
    T getValue(){ return this.value; }
}
