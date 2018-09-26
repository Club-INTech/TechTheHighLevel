package utils.math;

public  class Calculs {

    /**Cette méthode retourne le module d'un nombre*/

    public static double modulo(double number, double module){
        number = number%(2*module);
        if (number > module){
            number -= 2*module;
        }else if(number < -module){
            number += 2*module;
        }
        return number;
    }

    /**Cette méthode détermine si x est bien entre y et z*/

    public static boolean isBetween(double x, double y, double z) {
        //On inverse y et z si jamais on se trompe de paramètres
        if(y>z)
        {
            double t=z;
            z=y;
            y=t;
        }
        return x>=y && x<=z;
    }
}
