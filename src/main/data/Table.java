package data;

import data.table.MobileCircularObstacle;
import data.table.Obstacle;
import pfg.config.Config;
import utils.ConfigData;
import utils.Log;
import utils.container.Service;
import utils.maths.Segment;
import utils.maths.Vector;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Classe représentant la table et gérant les obstacles
 * // TODO Ajouter des logs
 */
public class Table implements Service
{
    /**
     * Graphe
     */
    private Graphe graphe;

    /**
     * Liste des obstacles fixes
     */
    private ArrayList<Obstacle> fixedObstacles;

    /**
     * Liste des obstacles mobiles
     */
    private ArrayList<MobileCircularObstacle> mobileObstacles;

    /**
     * Longueur de la table (en x, en mm)
     */
    private int length;

    /**
     * Longueur de la table (en y, en mm)
     */
    private int width;

    /**
     * Rayon du robot
     */
    private int robotRay;

    /**
     * Rayon du buddy robot !
     */
    private int buddyRobotRay;

    /**
     * Rayon des robots adverses
     */
    private int ennemyRobotRay;

    /**
     * Limite lorque l'on compare deux positions
     */
    private int compareThreshold;

    /**
     * Constructeur de la table
     */
    public Table() {
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
     * Met à jour la table à partir d'une liste de points représentant le centre les obstacles détectés
     * @param points    liste des centres des obstacles
     */
    public void updateMobileObstacles(ArrayList<Vector> points) {
        Iterator<MobileCircularObstacle> iterator = mobileObstacles.iterator();
        MobileCircularObstacle obstacle;
        Iterator<Vector> it = points.iterator();
        Vector point;
        Log.LIDAR.debug("Mise à jour des Obstacle...");

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

        for (Vector pt : points) {
            int ray = ennemyRobotRay;
            if (pt.quickDistanceTo(XYO.getBuddyInstance().getPosition()) < compareThreshold) {
                ray = buddyRobotRay;
            }
            MobileCircularObstacle obst = new MobileCircularObstacle(pt, ray);
            Log.LIDAR.debug("Obstacle mobile ajouté : " + obst);
            mobileObstacles.add(obst);
        }
        if (graphe != null) {
            this.graphe.update();
        } else {
            Log.LIDAR.warning("Graphe non instancié");
        }
        Log.LIDAR.debug("Mise à jour des obstacles terminées");
    }

    /**
     * Sert à savoir si la position se trouve ou non dans un obstacle fixe
     * @param point  position à tester
     */
    public boolean isPositionInFixedObstacle(Vector point) {
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
     * Sert à savoir si un segment intersecte l'un des obstacles
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
     * Ajoute un obstacle fixe à la table et met à jour le graphe
     * ATTENTION : méthode coûteuse car le graphe doit être recalculé
     * @param obstacle  nouvel obstacle
     */
    public void addFixedObstacle(Obstacle obstacle) {
        if (obstacle instanceof MobileCircularObstacle) {
            throw new IllegalArgumentException("L'obstacle ajouté n'est pas fixe !");
        }
        this.fixedObstacles.add(obstacle);
        if (graphe != null) {
            this.graphe.reInit();
        } else {
            Log.LIDAR.warning("Graphe non instancié");
        }
    }

    /**
     * Retire un obstacle fixe de la table et met à jour le graphe
     * ATTENTION : méthode coûteuse car le graphe doit être recalculé
     * @param obstacle  obstacle à retirer
     */
    public void removeFixedObstacle(Obstacle obstacle) {
        if (obstacle instanceof MobileCircularObstacle) {
            throw new IllegalArgumentException("L'obstacle ajouté n'est pas fixe !");
        }
        this.fixedObstacles.remove(obstacle);
        if (graphe != null) {
            this.graphe.reInit();
        } else {
            Log.LIDAR.warning("Graphe non instancié");
        }
    }

    /**
     * Getters & Setters
     */
    public Graphe getGraphe() {
        return this.graphe;
    }
    void setGraphe(Graphe graphe) {
        this.graphe = graphe;
    }
    public ArrayList<Obstacle> getFixedObstacles() {
        return fixedObstacles;
    }
    public ArrayList<MobileCircularObstacle> getMobileObstacles() {
        return mobileObstacles;
    }
    public int getLength() {
        return length;
    }
    public int getWidth() {
        return width;
    }

    /**
     * @see Service#updateConfig(Config)
     */
    @Override
    public void updateConfig(Config config) {
        this.robotRay = config.getInt(ConfigData.ROBOT_RAY);
        this.buddyRobotRay = config.getInt(ConfigData.BUDDY_RAY);
        this.ennemyRobotRay = config.getInt(ConfigData.ENNEMY_RAY);
        this.length = config.getInt(ConfigData.TABLE_X);
        this.width = config.getInt(ConfigData.TABLE_Y);
        this.compareThreshold = config.getInt(ConfigData.VECTOR_COMPARISON_THRESHOLD);
    }
}
