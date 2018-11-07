package data.graphe;

import utils.maths.Segment;

/**
 * Classe implémentant les arrêtes pour le graphe
 */
public class Ridge
{
    /**
     * Segment représentant l'arrête
     */
    private Segment seg;

    /**
     * Coût de l'arrête
     */
    private final int cost;

    /**
     * Disponibilité : true si franchissable
     */
    private boolean reachable;

    /**
     * Coût fixe de l'arrête
     */
    private static int staticCost;

    /**
     * Constructeur
     * @param cost  coût pour franchire l'arrête
     * @param segment   segment représentant l'arrête
     */
    public Ridge(int cost, Segment segment) {
        this.cost = cost + staticCost;
        this.reachable = true;
        this.seg = segment;
    }

    /** Getters & Setters */
    public Segment getSeg() {
        return seg;
    }
    public int getCost() {
        return cost;
    }
    public boolean isReachable() {
        return reachable;
    }
    public void setReachable(boolean reachable) {
        this.reachable = reachable;
    }
    public static void setStaticCost(int staticCost) {
        Ridge.staticCost = staticCost;
    }

}
