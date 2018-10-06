package data.table;

import data.table.obstacle.Obstacle;
import utils.math.Vec2;
import utils.math.VectCartesian;

import java.util.ArrayList;

/** SINGLETON stockant les données sur la table */
public class Table {

    private Vec2 entryPosition=new VectCartesian(0,0);
    private int sizeX=3000;
    private int sizeY=2000;
    private ArrayList<Obstacle> fixedObstacles;
    private ArrayList<Obstacle> mobileObstacles;

    /** Constructeur */
    public Table(){
        this.fixedObstacles=new ArrayList<>();
        this.mobileObstacles=new ArrayList<>();
    }

    /** Renvoie les obstacles fixes */
    public ArrayList<Obstacle> getFixedObstacles(){
        return this.fixedObstacles;
    }

    /** Ajoute un obstacle fixe */
    public void addFixedObstacle(Obstacle fixedObstacle){
        if (!this.fixedObstacles.contains(fixedObstacle)) {
            this.fixedObstacles.add(fixedObstacle);
        }
    }

    /** Renvoie les obstacles mobiles */
    public ArrayList<Obstacle> getMobileObstacles(){
        return this.mobileObstacles;
    }

    /** Ajoute un obstacle mobile */
    public void addMobileObstacle(Obstacle mobileObstacle){
        this.mobileObstacles.add(mobileObstacle);
    }



}
