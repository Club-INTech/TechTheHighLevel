package locomotion;

import data.Table;
import orders.OrderWrapper;
import pfg.config.Config;
import utils.container.Service;
import utils.math.Vec2;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Service permettant de suivre un chemin et de détecter les problèmes de suivit
 *
 * @author rem
 */
public class PathFollower extends Thread implements Service {

    /**
     * Order Wrapper
     */
    private OrderWrapper orderWrapper;

    /**
     * Table
     */
    private Table table;

    /**
     * Queue de communication avec Locomotion lors du suivit d'un chemin
     */
    private ConcurrentLinkedQueue<Vec2> pointsQueue;

    /**
     * Queue de communication avec Locomotion lors du suivit d'un chemin
     */
    private ConcurrentLinkedQueue<Exception> exceptionsQueue;

    /**
     * Construit le service de suivit de chemin
     * @param orderWrapper
     *              order wrapper
     * @param table
     *              table
     */
    private PathFollower(OrderWrapper orderWrapper, Table table) {
        this.orderWrapper = orderWrapper;
        this.table = table;
    }

    /**
     * Méthode permettant d'envoyer l'ordre d'avancer au LL et détecter les anomalies jusqu'à être arrivé
     * @param distance  distance de mouvement
     */
    public void moveLenghtwise(int distance) {

    }

    /**
     * Méthode permettant de tourner !
     * @param angle angle absolu vers lequel il faut se tourner
     */
    public void turn(double angle) {

    }

    /**
     * Vérifie en fonction de la vistesse s'il y a un adversaire en face du robot
     * @return  true s'il y a un adversaire devant
     */
    private boolean isTrajectoryObstructed() {
        return false;
    }

    @Override
    public void run() {
        super.run();
    }

    @Override
    public void interrupt() {
        super.interrupt();
    }

    /**
     * @see Service#updateConfig(Config)
     */
    @Override
    public void updateConfig(Config config) {

    }

    /**
     * Getters & Setters
     */
    void setPointsQueue(ConcurrentLinkedQueue<Vec2> pointsQueue) {
        this.pointsQueue = pointsQueue;
    }
    void setExceptionsQueue(ConcurrentLinkedQueue<Exception> exceptionsQueue) {
        this.exceptionsQueue = exceptionsQueue;
    }
}
