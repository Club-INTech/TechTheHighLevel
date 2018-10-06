package data.table.graph;

import data.table.obstacle.Obstacle;
import utils.math.Segment;
import utils.math.VectCartesian;

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
    public void createRidges(){
        Segment segment = new Segment(new VectCartesian(0,0), new VectCartesian(0,0));
        for (int i = 0; i<this.nodes.size()-1; i++){
            segment.setPointA(this.nodes.get(i).getPosition());
            for (int j = i+1; j<this.nodes.size(); j++){
                segment.setPointB(this.nodes.get(j).getPosition());
                for (Obstacle obstacle : this.fixedObstacles){
                    if (!obstacle.intersect(segment)){
                        this.nodes.get(i).addNeighbour(this.nodes.get(j), new Ridge((Segment)segment.clone()));
                        this.nodes.get(j).addNeighbour(this.nodes.get(i), new Ridge((Segment)segment.clone()));
                    }
                }
            }
        }
    }

    /** Définit si les ridges sont empruntables */
    public void updateRidges(){

    }
}
