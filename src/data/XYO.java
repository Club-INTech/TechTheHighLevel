package data;

import utils.math.Vec2;
import utils.math.VectCartesian;

/** SINGLETON qui stocke la position et l'orientation HAUT NIVEAU du robot */
public class XYO {

    private Vec2 position; //Position HAUT NIVEAU du robot
    private double orientation; //Orientation HAUT NIVEAU du robot

    /** Constructeur */
    XYO(){
        this.position=new VectCartesian(0,0);
        this.orientation=Math.PI;
    }

    /** Set la position du robot */
    void setPosition(Vec2 position){
        this.position.setX(position.getX());
        this.position.setY(position.getY());
    }

    /** Set la coordonnée X du robot */
    void setX(int x){
        this.position.setX(x);
    }

    /** Set la coordonnée Y du robot */
    void setY(int y){
        this.position.setY(y);
    }

    /** Set l'orientation du robot */
    void setOrientation(double orientation){
        this.orientation=orientation;
    }

    /** Renvoie la position du robot */
    Vec2 getPosition(){
        return this.position;
    }

    /** Renvoie l'orientation du robot */
    double getOrientation(){
        return this.orientation;
    }

}
