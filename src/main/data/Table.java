package data;

import data.table.obstacle.Obstacle;
import pfg.config.Config;
import utils.ConfigData;
import utils.container.Service;
import utils.math.Vec2;

import javax.naming.ConfigurationException;
import java.util.ArrayList;

/**
 * SINGLETON représentant la table
 *
 * @author sam, rem
 */
public class Table implements Service {

    /**
     * Dimension de la table
     * Override par la config
     */
    private int sizeX = 3000;
    private int sizeY = 2000;

    /**
     * Rayon du robot
     * Override par la config
     */
    private int robotRay;

    /**
     * Rayon du second robot
     * Override par la config
     */
    private int buddyRobotRay;

    /**
     * Rayon du robot adverse
     * Override par la config
     */
    private int ennemyRobotRay;

    /**
     * Liste des obstacles
     */
    private ArrayList<Obstacle> fixedObstacles;
    private ArrayList<Obstacle> mobileObstacles;

    /**
     * Constructeur
     */
    private Table() {
        this.fixedObstacles=new ArrayList<>();
        this.mobileObstacles=new ArrayList<>();
        this.initObstacle();
    }

    /**
     * Initialisation des obstacles fixes de la table
     */
    private void initObstacle() {
        // TODO : Remplir avec les obstacles de l'année !
    }

    /**
     * Méthode pour mettre à la liste d'obstacle mobile
     * @param point liste des centres des obstacles
     */
    public void updateMobileObstacles(ArrayList<Vec2> point){

    }

    /**
     * Ajoute un obstacle fixe
     * @param fixedObstacle     obstacle à ajouter
     */
    public void addFixedObstacle(Obstacle fixedObstacle){
        if (!this.fixedObstacles.contains(fixedObstacle)) {
            this.fixedObstacles.add(fixedObstacle);
        }
    }

    /**
     * Ajoute une liste d'obstacles fixes
     * @param fixedObstacles    obstacles à ajouter
     */
    public void addFixedObstacles(ArrayList<Obstacle> fixedObstacles){
        for (Obstacle obstacle : fixedObstacles){
            if (!this.fixedObstacles.contains(obstacle)){
                this.fixedObstacles.add(obstacle);
            }
        }
    }

    /**
     * @see Service#updateConfig(Config)
     */
    @Override
    public void updateConfig(Config config) {
        robotRay = config.getInt(ConfigData.ROBOT_RAY);
        buddyRobotRay = config.getInt(ConfigData.BUDDY_RAY);
        ennemyRobotRay = config.getInt(ConfigData.ENNEMY_RAY);
    }

    /**
     * Getters
     */
    public ArrayList<Obstacle> getFixedObstacles(){
        return this.fixedObstacles;
    }
    public ArrayList<Obstacle> getMobileObstacles(){
        return this.mobileObstacles;
    }
}
