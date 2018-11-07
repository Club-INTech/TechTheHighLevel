package data;

import data.table.Obstacle;
import pfg.config.Config;
import utils.ConfigData;
import utils.container.Service;
import utils.math.Segment;
import utils.math.Vec2;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * SINGLETON représentant la table et gérant les obstacles
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
     * Graphe
     */
    private Graph graphe;

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
        this.fixedObstacles = new ArrayList<>();
        this.mobileObstacles = new ArrayList<>();
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
     * @param points liste des centres des obstacles
     */
    public void updateMobileObstacles(ArrayList<Vec2> points){
        Iterator<Obstacle> iterator = mobileObstacles.iterator();
        Obstacle obstacle;
        Iterator<Vec2> it = points.iterator();
        Vec2 point;

        while (iterator.hasNext()) {
            obstacle = iterator.next();
            while (it.hasNext()) {
                point = it.next();
                if (obstacle.isInObstacle(point)) {
                    obstacle.update(point);
                    it.remove();
                }
            }
            if (obstacle.getOutDatedTime() < System.currentTimeMillis()) {
                iterator.remove();
            }
        }

        // TODO : Itérer sur les points restant pour créer les nouveaux obstacles
        // ATTENTION : Penser à prendre en compte le deuxième robot...
        // + Mettre à jour le graphe s'il a été instancié
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
     * @param point  position à tester
     * @return  true si le point est dans l'un des obstacles fixes
     */
    public boolean isPositionInFixedObstacle(Vec2 point) {
        Iterator<Obstacle> iterator = fixedObstacles.iterator();
        Obstacle obstacle;
        while (iterator.hasNext()) {
            obstacle = iterator.next();
            if (obstacle.isInObstacle(point)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param segment   segment à tester
     * @return  true si le segment intersecte l'un des obstacles fixes
     */
    public boolean intersectAnyFixedObstacle(Segment segment) {
        Iterator<Obstacle> iterator = fixedObstacles.iterator();
        Obstacle obstacle;
        while (iterator.hasNext()) {
            obstacle = iterator.next();
            if (obstacle.intersect(segment)) {
                return true;
            }
        }
        return false;
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
    public void setGraphe(Graph graphe) {
        this.graphe = graphe;
    }
}
