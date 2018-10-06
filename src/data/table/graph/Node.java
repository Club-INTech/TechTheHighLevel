package data.table.graph;

import utils.math.Vec2;

import java.util.ArrayList;

/** Point dans le graphe */
public class Node {

    private Vec2 position; //Position de la node sur la table
    private ArrayList<Node> neighbours; //Liste des nodes voisines (ie accessibles lorsqu'il n'y a pas d'obstacle)
    private ArrayList<Ridge> neighboursRidges;

    //Valeur de priorité dans la recherche des nodes (utile et mis à jour lors de l'exécution du pathfinding)
    private int heuristique = 0;
    //Cout de la node (utile et mis à jour lors de l'exécution du pathfinding)
    private int cost = 0;
    //Node prédécesseur (utile et mis à jour lors de l'exécution du pathfinding)
    private Node predecessor;

    /** Constructeur */
    public Node(Vec2 position){
        this.position=position;
        this.neighbours=new ArrayList<>();
        this.neighboursRidges =new ArrayList<>();
    }

    /** Renvoie la position da la node */
    public Vec2 getPosition(){
        return this.position;
    }

    /** Renvoie les voisins de la node*/
    public ArrayList<Node> getNeighbours(){
        return this.neighbours;
    }

    /** Renvoie les voisins de la node*/
    public ArrayList<Ridge> getNeighboursRidges(){
        return this.neighboursRidges;
    }

    /** Ajoute un voisin à cette node */
    public void addNeighbour(Node neighbour, Ridge ridge){
        this.neighbours.add(neighbour);
        this.neighboursRidges.add(ridge);
    }

    /** Supprime un voisin de cette node */
    public void removeNeighbour(Node neighbour){
        for (Ridge ridge : (ArrayList<Ridge>)this.getNeighboursRidges().clone()){
            if (ridge.containsNode(this)){
                this.getNeighboursRidges().remove(ridge);
            }
        }
        this.neighbours.remove(neighbour);
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
