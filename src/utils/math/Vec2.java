package utils.math;

public class Vec2 {

    /**abscisse du vecteur*/
    private int x;

    /**ordonnée du vecteur*/
    private int y;

    /**coordonnée radiale*/
    private double r;

    /**angle polaire du point*/
    private double a;

    /**
     * Constructeur d'un vecteur nul
     */
    public Vec2(){
        this.x = 0;
        this.y = 0;
        this.r = 0;
        this.a = 0;
    }

    /**
     * Constructeur d'un vecteur en coordonnées cartésiennes
     * @param x
     * @param y
     */
    public Vec2(int x, int y) {
        this.x = x;
        this.y = y;
        this.r = Math.sqrt(x*x+y*y);
        this.a = this.angle();
    }

    /**
     * Constructeur d'un vecteur en coordonnées polaires
     * @param r
     * @param a
     */
    public Vec2(double r, double a) {
        this.r = r;
        this.a = a;
        this.x = 0;
        this.y = 0;
    }

    /**
     * Produit scalaire
     * @param vecteur
     * @return
     */
    public int dot(Vec2 vecteur){
        return 0;
    }

    /**
     * Produit vectoriel
     * @param vecteur
     * @return
     */
    public int crossProduct(Vec2 vecteur){
        return 0;
    }
    /**On rajoute un autre vecteur et on retourne le nouveau*/
    public Vec2 plusVector(Vec2 vecteur){
        return null;
    }

    /**On retranche un vecteur et on retourne le nouveau*/
    public Vec2 minusVector(Vec2 vecteur){
        return null;
    }

    /**On rajoute un vecteur*/
    public void plus(Vec2 vecteur){

    }

    /**On retranche un vecteur*/
    public void minus(Vec2 vecteur){

    }

    /**Distance à un vecteur*/
    public float distanceTo(Vec2 vecteur){
        return 0;
    }

    /**retourne vrai si les deux vecteurs sont égaux*/
    public boolean equals(Vec2 vecteur){
        return false;
    }

    /**On renvoie un vecteur multiplié par un réel*/
    public Vec2 dotFloat(float a){
        return null;
    }

    /**On retourne un vecteur en coordonnées polaires*/
    public Vec2 toPolar(){
        return null;
    }

    /**On retourne un vecteur en cartésien*/
    public Vec2 toCartesian(){
        return null;
    }

    /**On calcule l'angle du vecteur*/
    public double angle(){
        return 0;
    }

}
