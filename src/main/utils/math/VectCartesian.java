package utils.math;

/**
 * Classe héritante de Vec2 dont la fonction est de différencier son constructeur de Vec2
 * @see Vec2
 */
public class VectCartesian extends Vec2 {

    /**
     * Constructeur d'un vecteur cartésien pour qu'il n'y ait pas de confusion avec les vecteurs polaires quand on débug
     * @param x
     * @param y
     */
    public VectCartesian(float x, float y){
        super(Math.round(x), Math.round(y));
    }
}
