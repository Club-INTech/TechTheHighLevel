package data;

class Data{

    /** Valeur stockée dans l'objet */
    private Object value;

    Data (Object value){
        this.value=value;
    }

    /** Permet de définir la valeur stockée dans cet objet */
    void setValue(Object value){
        this.value=value;
    }

    /** Permet de récupérer la valeur stockée dans cet objet */
    Object getValue(){ return this.value; }
}
