package locomotion;

import data.Graphe;
import data.Table;
import data.XYO;
import pfg.config.Config;
import utils.container.Service;
import utils.math.Vec2;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Service permettant au robot de se déplacer
 *
 * @author rem
 */
public class Locomotion implements Service {

    /**
     * Service de recherche de chemin
     */
    private Pathfinder pathfinder;

    /**
     * Service de suivit de chemin
     */
    private PathFollower pathFollower;

    /**
     * Table
     */
    private Table table;

    /**
     * Graphe
     */
    private Graphe graphe;

    /**
     * Position & Orientation du robot
     */
    private XYO xyo;

    /**
     * Files de communication avec le PathFollower
     */
    private ConcurrentLinkedQueue<Vec2> pointsQueue;
    private ConcurrentLinkedQueue<Exception> exceptionsQueue;

    /**
     * Construit le service de locmotion
     * @param table
     *              table
     * @param graphe
     *              graphe permettant de trouver les chemins
     * @param pathFollower
     *              service de suivit de chemin
     */
    private Locomotion(Table table, Graphe graphe, Pathfinder pathfinder, PathFollower pathFollower) {
        this.table = table;
        this.graphe = graphe;
        this.pathFollower = pathFollower;
        this.pathfinder = pathfinder;
        this.xyo = XYO.getRobotInstance();
        this.pointsQueue = new ConcurrentLinkedQueue<>();
        this.exceptionsQueue = new ConcurrentLinkedQueue<>();
        pathFollower.setPointsQueue(pointsQueue);
        pathFollower.setExceptionsQueue(exceptionsQueue);
    }

    /**
     * Méthode permettant au robot d'avancer : bloquant
     * @param distance  distance de translation
     */
    public void moveLenghtwise(int distance) {

    }

    /**
     * Méthode permettant au robot de tourner
     * @param angle angle absolu vers lequel il faut se tourner
     */
    public void turn(double angle) {

    }

    /**
     * Méthode permettant au robot de tourner
     * @param angle angle relatif de rotation
     */
    public void turnRelative(double angle) {

    }

    /**
     * Méthode permettant au robot de se déplacer jusqu'à un point de la table
     * @param point point à atteindre
     */
    public void moveToPoint(Vec2 point) {

    }

    /**
     * @see Service#updateConfig(Config)
     */
    @Override
    public void updateConfig(Config config) {

    }
}
