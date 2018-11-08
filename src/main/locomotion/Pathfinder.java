package locomotion;

import data.Graphe;
import pfg.config.Config;
import utils.container.Service;
import utils.math.Vec2;

import java.util.ArrayList;

/**
 * Service déstiner à calculer un chemin entre deux points de la table
 * TODO : 1As, Compléter
 */
public class Pathfinder implements Service {

    /**
     * Graphe de recherche de chemin
     */
    private Graphe graphe;

    /**
     * Construit un pathfinder
     * @param graphe    graphe paramétrant la table
     */
    private Pathfinder(Graphe graphe) {
        this.graphe = graphe;
    }

    /**
     * Algorithme permettant de calculer le chemin
     * @param start point de départ
     * @param aim   point d'arrivé
     * @return      le plus court chemin du point de départ au point d'arriver
     */
    public ArrayList<Vec2> findPath(Vec2 start, Vec2 aim) {
        return null;
    }

    @Override
    public void updateConfig(Config config) {

    }
}
