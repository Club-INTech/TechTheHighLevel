package utils.math;

/**
 * Il s'agit d"une classe définissant des méthodes de calculs spécifiques pour les vecteurs, le constructeur de cette classe est protected
 */
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
    protected Vec2(){
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
    protected Vec2(int x, int y) {
        this.x = x;
        this.y = y;
        this.r = Math.sqrt(x*x+y*y);
        this.a = this.angle();
    }

    /**
     * Constructeur d'un vecteur en coordonnées polaires
     * @param r rayon
     * @param a angle
     */
    protected Vec2(double r, double a) {
        this.r = r;
        this.a = a;
        this.x = (int)(r*Math.cos(a));
        this.y = (int)(r*Math.sin(a));
    }

    /**
     * Produit scalaire
     * @param vecteur
     * @return
     */
    public int dot(Vec2 vecteur){
        return this.x * vecteur.getX() + this.y*vecteur.getY();
    }

    /**
     * Produit vectoriel
     * @param vecteur
     * @return
     */
    public int crossProduct(Vec2 vecteur){
        return x * vecteur.getY() - y * vecteur.getX();
    }

    /**On rajoute un autre vecteur et on retourne le nouveau*/
    public Vec2 plusVector(Vec2 vecteur){

        return new Vec2(this.x + vecteur.getX(), this.y + vecteur.getY());
    }

    /**On retranche un vecteur et on retourne le nouveau*/
    public Vec2 minusVector(Vec2 vecteur){
        return new Vec2(this.x - vecteur.getX(), this.y - vecteur.getY());
    }

    /**On rajoute un vecteur*/
    public void plus(Vec2 vecteur){
        this.x+=vecteur.getX();
        this.y+=vecteur.getY();
        this.r+=vecteur.getR();
        this.a+=vecteur.getA();
    }

    /**On retranche un vecteur*/
    public void minus(Vec2 vecteur){
        this.x-=vecteur.getX();
        this.y-=vecteur.getY();
        this.r-=vecteur.getR();
        this.a-=vecteur.getA();
    }

    /**Distance à un vecteur*/
    public double distanceTo(Vec2 other){
        int x2=(this.x - other.getX()) * (this.x - other.getX());
        int y2=(this.y - other.getY()) * (y - other.getY());
        return Math.sqrt( x2 +y2 );
    }

    /**retourne vrai si les deux vecteurs sont égaux*/
    public boolean equals(Vec2 vecteur){

        return vecteur.getX() == this.getX() && vecteur.getY() == this.getY() ;
    }

    /**On renvoie un vecteur multiplié par un réel*/
    public Vec2 dotFloat(float a){

        return new Vec2((int)a*this.x, (int)a*this.y);
    }

    @Override
    public Vec2 clone() {
        return new Vec2(this.x, this.y);
    }


    /**On calcule l'angle du vecteur entre -pi et pi (non incluses )*/
    public double angle(){
        return Math.atan2(this.y,this.x);
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }
}
