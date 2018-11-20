/**
 * Copyright (c) 2018, INTech.
 * this file is part of INTech's HighLevel.

 * INTech's HighLevel is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * INTech's HighLevel is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with it.  If not, see <http://www.gnu.org/licenses/>.
 **/

package locomotion;

import data.Graphe;
import data.graphe.Node;
import data.graphe.Ridge;
import pfg.config.Config;
import utils.container.Service;
import utils.math.Vec2;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Service déstiner à calculer un chemin entre deux points de la table
 * TODO : 1As
 */
public class Pathfinder implements Service {

    /**
     * Graphe de recherche de chemin
     */
    private Graphe graphe;

    /**
     * Liste des noeuds à visité
     */
    private PriorityQueue<Node> openList;

    /**
     * Liste des noeuds déjà visités
     */
    private ArrayList<Node> closedList;

    /**
     * Construit un pathfinder
     * @param graphe    graphe paramétrant la table
     */
    private Pathfinder(Graphe graphe) {
        this.graphe = graphe;
    }

    /**
     * Algorithme permettant de calculer le chemin entre deux noeuds du graphe
     * @param start noeud de départ
     * @param aim   noeud d'arrivé
     * @return      le plus court chemin du noeud de départ au noeud d'arrivé
     * @throws NoPathFound
     *              s'il n'existe pas de chemin entre les deux noeuds
     */
    public ArrayList<Vec2> findPath(Node start, Node aim) throws NoPathFound {
        Node currentNode;
        Set<Node> neighbours;
        int currentCost;

        openList.clear();
        openList.add(start);

        while (!openList.isEmpty()) {
            currentNode = openList.poll();

            if (currentNode.equals(aim)) {
                return reconstructPath(start, aim);
            }
            neighbours = currentNode.getNeighbours().keySet();

            for (Node neighbour : neighbours) {
                Ridge ridge = currentNode.getNeighbours().get(neighbour);
                if (ridge.isReachable()) {
                    currentCost = currentNode.getCout() + ridge.getCost();
                    if ((openList.contains(neighbour) || closedList.contains(neighbour)) && currentCost < neighbour.getCout()) {
                        neighbour.setCout(currentCost);
                        neighbour.setPred(currentNode);
                        if (closedList.contains(neighbour)) {
                            closedList.remove(neighbour);
                            openList.add(neighbour);
                        }
                    } else if (!(openList.contains(neighbour) || closedList.contains(neighbour))) {
                        neighbour.setCout(currentCost);
                        neighbour.setPred(currentNode);
                        openList.add(neighbour);
                    }
                }
            }
            closedList.add(currentNode);
        }

        throw new NoPathFound(aim.getPosition());
    }

    /**
     * Méthode permettant de reconstruire un chemin trouvé à partir des prédecesseurs de chaque noeud
     * @param start noeud de départ du chemin
     * @param aim   noeud d'arriver
     */
    private ArrayList<Vec2> reconstructPath(Node start, Node aim) {
        Node currentNode = aim;
        ArrayList<Vec2> path = new ArrayList<>();

        do {
            path.add(0, currentNode.getPosition());
            currentNode = currentNode.getPred();
        } while (currentNode != null && !(currentNode.equals(start)));
        return path;
    }

    @Override
    public void updateConfig(Config config) {

    }
}
