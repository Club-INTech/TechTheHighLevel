package locomotion;

import data.Graphe;
import data.Table;
import data.XYO;
import data.graphe.Node;
import pfg.config.Config;
import utils.container.Service;
import utils.math.Calculs;
import utils.math.Vec2;
import utils.math.VectCartesian;

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
    public void moveLenghtwise(int distance) throws UnableToMoveException {
        pathFollower.moveLenghtwise(distance, false);
    }

    /**
     * Méthode permettant au robot d'avancer : bloquant
     * @param distance
     *              distance de translation
     * @param expectedWallImpact
     *              true si l'on veut ignorer les blocages mécaniques
     */
    public void moveLenghtwise(int distance, boolean expectedWallImpact) throws UnableToMoveException {
        pathFollower.moveLenghtwise(distance, expectedWallImpact);
    }

    /**
     * Méthode permettant au robot de tourner
     * @param angle angle absolu vers lequel il faut se tourner
     */
    public void turn(double angle) throws UnableToMoveException {
        pathFollower.turn(angle, false);
    }

    /**
     * Méthode permettant au robot de tourner
     * @param angle angle relatif de rotation
     */
    public void turnRelative(double angle) throws UnableToMoveException {
        angle = Calculs.modulo(angle + xyo.getOrientation(), 2*Math.PI);
        pathFollower.turn(angle, false);
    }

    /**
     * Méthode permettant au robot de se déplacer jusqu'à un point de la table
     * @param point point à atteindre
     */
    public void moveToPoint(Vec2 point) {
        // TODO : Compléter
        Node start;
        Node aim;

        if (table.isPositionInFixedObstacle(point) || table.isPositionInFixedObstacle(xyo.getPosition())) {

        }
    }

    /**
     * @see Service#updateConfig(Config)
     */
    @Override
    public void updateConfig(Config config) {

    }
}
