package utils.math;

/**
 * Il s'agit d"une classe définissant des méthodes de calculs spécifiques pour les vecteurs, le constructeur de cette classe est protected
 */
public abstract class Vec2 {

    /**abscisse du vecteur*/
    private int x;

    /**ordonnée du vecteur*/
    private int y;

    /**coordonnée radiale*/
    private double r;

    /**calculateAngle polaire du point*/
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
        this.a = this.calculateAngle();
    }

    /**
     * Constructeur d'un vecteur en coordonnées polaires
     * @param r rayon
     * @param a calculateAngle
     */
    protected Vec2(double r, double a) {
        this.r = r;
        this.a = a;
        this.x = (int)Math.round((r*Math.cos(a)));
        this.y = (int)Math.round((r*Math.sin(a)));
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

        return new VectCartesian(this.x + vecteur.getX(), this.y + vecteur.getY());
    }

    /**On retranche un vecteur et on retourne le nouveau*/
    public Vec2 minusVector(Vec2 vecteur){
        return new VectCartesian(this.x - vecteur.getX(), this.y - vecteur.getY());
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
    public boolean equals(Object obj){
        if (obj instanceof Vec2) {
            return ((Vec2) obj).getX() == this.getX() && ((Vec2) obj).getY() == this.getY();
        }
        else{
            return false;
        }
    }

    /**On renvoie un vecteur multiplié par un réel*/
    public Vec2 dotFloat(float a){
        return new VectCartesian(Math.round(a*this.x), Math.round(a*this.y));
    }

    @Override
    public Vec2 clone() {
        return new VectCartesian(this.x, this.y);
    }

    /**On calcule l'calculateAngle du vecteur entre -pi et pi (non incluses )*/
    private double calculateAngle(){
        if (this.squaredLength() == 0)
            return 0;

        double a = Math.min((double) Math.abs(x), Math.abs(y)) / Math.max(Math.abs(x), Math.abs(y));
        double s = a * a;
        double r = ((-0.0464964749 * s + 0.15931422) * s - 0.327622764) * s * a + a;

        if (Math.abs(y) > Math.abs(x))
            r = 1.57079637 - r;
        if (x < 0)
            r = 3.14159274 - r;
        if (y < 0)
            r = -r;
        return r;
    }

    public double squaredLength(){
        return (int) (r * r);
    }

    public VectCartesian symetrize(){
        this.x=-x;
        return new VectCartesian(x,y);
    }


    /** Renvoie la position X du vecteur */

    public int getX() {
        return x;
    }

    /** Set la position X du vecteur */
    public void setX(int x) {
        this.x = x;
    }

    /** Renvoie la position Y du vecteur */
    public int getY() {
        return y;
    }

    /** Set la position Y du vecteur */
    public void setY(int y) {
        this.y = y;
    }

    /** Renvoie le rayon R du vecteur */
    public double getR() {
        return r;
    }

    /** Set le rayon R du vecteur */
    public void setR(double r) {
        this.r = r;
    }

    /** Renvoie l'angle A du vecteur */
    public double getA() {
        return a;
    }

    /** Set l'angle A du vecteur */
    public void setA(double a) {
        this.a = a;
    }


    @Override
    /** Renvoie les coordonnées x et y du Vec2*/
    public String toString() {
        return String.format("(%s,%s)",this.x,this.y);
    }



    /**
     * Lorsque l'on discute avec le LL, on signifie que c'est un vecteur comme ceci
     */
    public String toStringEth(){
        return x + " " + y;
    }






}
