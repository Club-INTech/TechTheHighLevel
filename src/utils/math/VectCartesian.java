package utils.math;

public class VectCartesian extends Vec2 {

    /**
     * Constructeur d'un vecteur cartésien pour qu'il n'y ait pas de confusion avec les vecteurs polaires quand on débug
     * @param x
     * @param y
     */
    public VectCartesian(int x, int y){
        super(x, y);
    }
}
