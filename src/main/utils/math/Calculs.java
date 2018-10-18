package main.utils.math;

/**
 * On fait des calculs à l'aide des méthodes de cette classe
 */
public class Calculs {

    /**Cette méthode retourne le nombre congru à un nombre modulo un module
     * @param number nombre dont on veut calculer le nombre qui lui est congru
     * @param module le modole*/

    public static double modulo(double number, double module){
        number = number%(2*module);
        if (number > module){
            number -= 2*module;
        }else if(number < -module){
            number += 2*module;
        }
        return number;
    }

    /**Cette méthode détermine si x est bien entre y et z
     * @param x nombre dont on veut savoir s'il est entre les deux autres nombres spécifiés
     *@param y plus petit nombre
     * @param z plus grand nombre*/

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
