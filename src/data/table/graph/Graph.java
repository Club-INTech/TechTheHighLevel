package data.table.graph;

import data.table.obstacle.Obstacle;

import java.util.ArrayList;

/** Graphe regroupant les nodes et les arêtes pour le pathfinding */
public class Graph {

    public ArrayList<Node> nodes;
    public ArrayList<Obstacle> fixedObstacles;

    /** Constructeur */
    public Graph(){
        this.nodes=new ArrayList<>();
        this.fixedObstacles=new ArrayList<>();
    }

    /** Initialise le graph */
    public void initGraph(){

    }

    /** Ajoute une node au graph */
    public void addNode(Node node){
        this.nodes.add(node);
    }

    /** Enlève la node du graph */
    public void removeNode(Node node){
        this.nodes.remove(node);
    }

    /** Définit les ridges */
    public void createRidge(){

    }

    /** Définit si les ridges sont empruntables */
    public void updateRidges(){

    }
}
