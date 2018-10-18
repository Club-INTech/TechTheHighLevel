package main.data.table;

import main.data.table.obstacle.Obstacle;
import main.utils.math.Vec2;
import main.utils.math.VectCartesian;

import java.util.ArrayList;

/** SINGLETON stockant les données sur la table */
public class Table {

    /** Position d'entrée du robot */
    private Vec2 entryPosition = new VectCartesian(0,0); // A set dans la config

    /** Dimension de la table */
    private int sizeX = 3000;
    private int sizeY = 2000;

    /** Rayons des robots */
    private int mainRobotRay = 26; // A set dans la config
    private int secondaryRobotRay = 20; // A set dans la config
    private int ennemyRobotRay = 10; // A set dans la config

    /** Liste des obstacles */
    private ArrayList<Obstacle> fixedObstacles;
    private ArrayList<Obstacle> mobileObstacles;

    /** Constructeur */
    public Table(){
        this.fixedObstacles=new ArrayList<>();
        this.mobileObstacles=new ArrayList<>();
    }

    /** Méthode pour mettre à la liste d'obstacle mobile */

    public void updateObstacle(){

    }

    /** Ajoute un obstacle fixe */
    public void addFixedObstacle(Obstacle fixedObstacle){
        if (!this.fixedObstacles.contains(fixedObstacle)) {
            this.fixedObstacles.add(fixedObstacle);
        }
    }

    /** Ajoute une liste d'obstacles fixes */
    public void addFixedObstacles(Obstacle[] fixedObstacles){
        for (Obstacle obstacle : fixedObstacles){
            if (!this.fixedObstacles.contains(obstacle)){
                this.fixedObstacles.add(obstacle);
            }
        }
    }

    /** Ajoute une liste d'obstacles fixes */
    public void addFixedObstacles(ArrayList<Obstacle> fixedObstacles){
        for (Obstacle obstacle : fixedObstacles){
            if (!this.fixedObstacles.contains(obstacle)){
                this.fixedObstacles.add(obstacle);
            }
        }
    }

    /** Renvoie les obstacles fixes */
    public ArrayList<Obstacle> getFixedObstacles(){
        return this.fixedObstacles;
    }

    /** Ajoute un obstacle mobile */
    public void addMobileObstacle(Obstacle mobileObstacle){
        if (!this.mobileObstacles.contains(mobileObstacle)) {
            this.mobileObstacles.add(mobileObstacle);
        }
    }

    /** Ajoute une liste d'obstacles mobiles */
    public void addMobileObstacles(Obstacle[] mobileObstacles){
        for (Obstacle obstacle : mobileObstacles){
            if (!this.mobileObstacles.contains(obstacle)){
                this.mobileObstacles.add(obstacle);
            }
        }
    }

    /** Ajoute une liste d'obstacles mobiles */
    public void addMobileObstacles(ArrayList<Obstacle> mobileObstacles){
        for (Obstacle obstacle : mobileObstacles){
            if (!this.mobileObstacles.contains(obstacle)){
                this.mobileObstacles.add(obstacle);
            }
        }
    }

    /** Renvoie les obstacles mobiles */
    public ArrayList<Obstacle> getMobileObstacles(){
        return this.mobileObstacles;
    }

    /** Supprime un obstacle mobile */
    public void removeMobileObstacle(Obstacle mobileObstacleToRemove){
        this.mobileObstacles.remove(mobileObstacleToRemove);
    }



}
