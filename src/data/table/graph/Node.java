package data.table.graph;

import utils.math.Vec2;

import java.util.HashMap;
import java.util.Map;

/** Point dans le graphe */
public class Node {

    private Vec2 position; //Position de la node sur la table
    private HashMap<Ridge, Node> neighbours; //Liste des nodes voisines (ie accessibles lorsqu'il n'y a pas d'obstacle)

    //Valeur de priorité dans la recherche des nodes (utile et mis à jour lors de l'exécution du pathfinding)
    private int heuristique = 0;
    //Cout de la node (utile et mis à jour lors de l'exécution du pathfinding)
    private int cost = 0;
    //Node prédécesseur (utile et mis à jour lors de l'exécution du pathfinding)
    private Node predecessor;

    /** Constructeur */
    public Node(Vec2 position){
        this.position=position;
        this.neighbours=new HashMap<>();
    }

    /** Renvoie la position da la node */
    public Vec2 getPosition(){
        return this.position;
    }

    /** Renvoie les voisins de la node*/
    public HashMap<Ridge,Node> getNeighbours(){
        return this.neighbours;
    }


    /** Ajoute un voisin à cette node */
    public void addNeighbour(Node neighbour, Ridge ridge){
        this.neighbours.putIfAbsent(ridge, neighbour);
    }

    /** Supprime un voisin de cette node à partir du ridge le composant*/
    public void removeNeighbour(Ridge ridge){
        this.neighbours.remove(ridge);
    }

    @Override
    /** Renvoie si la node a les mêmes coordonnées que la node en argument */
    public boolean equals(Object obj){
        if (obj instanceof Node){
            return this.position.equals(((Node) obj).position);
        }
        else {
            return false;
        }
    }

    @Override
    /** Renvoie la position de la node */
    public String toString() {
        return this.position.toString();
    }
}
