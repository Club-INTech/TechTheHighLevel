package main.utils.math;

/**
 * @see VectCartesian
 */
public class VectPolar extends Vec2 {

    /**
     * Constructeur d'un vecteur polaire pour qu'il n'y ait pas de confusion avec les vecteurs cartésiens
     * @param r
     * @param a
     */
    public VectPolar(double r, double a){
        super(r,a);
    }
}
