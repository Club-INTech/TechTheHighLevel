package utils.maths;

/** Lib de methodes statiques pour calculs mathématiques */
public class MathLib {

    /** Modulo qui calcul entre -module/2 inclue et +module/2 non inclue
     * @param value     valeur à calculer
     * @param module    module
     */
    public static double modulo(double value, double module){
        value = value%module;
        if (value >= module/2){
            value-=module;
        }
        else if(value < -module/2){
            value+= module;
        }
        return value;
    }
}
