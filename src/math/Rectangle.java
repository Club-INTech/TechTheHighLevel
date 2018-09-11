package math;


public class Rectangle extends Shape {

    /** longueur du rectangle*/
    private float length;

    /**largeur du rectangle*/
    private float width;

    /**Constructeur*/
    public Rectangle(int xCenter, int yCenter, float length, float width) {
        super(xCenter, yCenter);
    }

    /**Cette méthode retourne vrai s'il y'a intersection entre le segment et le rectangle*/
    @Override
    public boolean intersectsWithSegment(Segment segment) {
        return false;
    }

    /**Cette méthode retourne vrai si le rectangle contient le cercle*/
    @Override
    public boolean containsCircle(Circle circle) {
        return false;
    }

    /**
     * Cette méthode retourne true s'il y'a intersection entre deux rectangles
     * @param rectangle
     * @return
     */
    public boolean intersectsWithRectangle(Rectangle rectangle){
        return false;
    }

    /**
     * Cette méthode retourne les diagonales d'un rectangle
     * @return
     */
    public Segment[] getDiagonals(){
        return null;
    }

    /**
     * Cette méthode change les dimensions du rectangle
     * @param length : longueur du rectangle
     * @param width: largeur du rectangle
     */
    public void changeDimensions(float length, float width){
        this.length=length;
        this.width=width;
    }

    /**getter de la longueur*/
    public float getLength() {
        return length;
    }

    /**getter de la largeur*/
    public float getWidth() {
        return width;
    }

    /**setter de la longueur*/
    public void setLength(float length) {
        this.length = length;
    }

    /**setter de la largeur*/
    public void setWidth(float width) {
        this.width = width;
    }
}
