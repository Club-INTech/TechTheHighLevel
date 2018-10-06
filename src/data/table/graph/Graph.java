package data.table.graph;

import data.table.obstacle.Obstacle;
import utils.math.Segment;
import utils.math.VectCartesian;

import java.util.ArrayList;

/** Graphe regroupant les nodes et les arêtes pour le pathfinding */
public class Graph {

    private ArrayList<Node> nodes;
    private ArrayList<Ridge> ridges;
    private ArrayList<Obstacle> fixedObstacles;
    private ArrayList<Obstacle> mobileObstacles;

    /** Constructeur */
    public Graph(){
        this.nodes=new ArrayList<>();
        this.ridges=new ArrayList<>();
        this.fixedObstacles=new ArrayList<>();
        this.mobileObstacles=new ArrayList<>();
    }

    /** Initialise le graph */
    public void initGraph(){
        createRidges();
    }

    /** Ajoute une node au graph */
    public void addNode(Node node){
        if (!this.nodes.contains(node)) {
            this.nodes.add(node);
        }
    }

    /** Enlève la node du graph */
    public void removeNode(Node nodeWantedToRemove){
        Node nodeToRemove=null;
        //On cherche si la node existe dans le graphe
        for (Node node : this.nodes){
            if (nodeWantedToRemove.equals(node)){
                nodeToRemove=node;
                break;
            }
        }
        if (nodeToRemove!=null) {
            //On
            for (Ridge ridgeToRemove : nodeToRemove.getNeighboursRidges()) {
                this.ridges.remove(ridgeToRemove);
            }
            for (Node neighbourNode : nodeToRemove.getNeighbours()) {
                neighbourNode.removeNeighbour(nodeToRemove);
            }
            this.nodes.remove(nodeToRemove);
        }
    }

    /** Renvoie toutes les nodes de la table*/
    public ArrayList<Node> getNodes(){
        return this.nodes;
    }

    /** Renvoie tous les ridges de la table*/
    public ArrayList<Ridge> getRidges(){
        return this.ridges;
    }

    /** Définit les ridges */
    public void createRidges(){
        Segment segment = new Segment(new VectCartesian(0,0), new VectCartesian(0,0));
        boolean isIntersectingWithFixedObstacles=false;
        for (int i = 0; i<this.nodes.size()-1; i++){
            segment.setPointA(this.nodes.get(i).getPosition());
            for (int j = i+1; j<this.nodes.size(); j++){
                isIntersectingWithFixedObstacles=false;
                segment.setPointB(this.nodes.get(j).getPosition());
                for (Obstacle obstacle : this.fixedObstacles){
                    if (!obstacle.intersect(segment)){
                        isIntersectingWithFixedObstacles=true;
                        break;
                    }
                }
                if (!isIntersectingWithFixedObstacles){
                    Ridge ridge = new Ridge(segment.clone());
                    this.ridges.add(ridge);
                    this.nodes.get(i).addNeighbour(this.nodes.get(j), ridge);
                    this.nodes.get(j).addNeighbour(this.nodes.get(i), ridge);
                }
            }
        }
    }

    /** Définit quels ridges sont utilisables */
    public void updateRidges(){
        boolean isIntersecting = false;
        for (Node node : this.nodes){
            for (Ridge ridge : node.getNeighboursRidges()) {
                isIntersecting = false;
                for (Obstacle obstacle : this.fixedObstacles) {
                    if (obstacle.intersect(ridge.getSegment())) {
                        isIntersecting = true;
                        break;
                    }
                }
                if (!isIntersecting) {
                    for (Obstacle obstacle : this.mobileObstacles) {
                        if (obstacle.intersect(ridge.getSegment())) {
                            isIntersecting = true;
                            break;
                        }
                    }
                }
                if (isIntersecting) {
                    ridge.setUsable(false);
                }
                else{
                    ridge.setUsable(true);
                }
            }
        }
    }

    /** Renvoie les obstacles fixes */
    public ArrayList<Obstacle> getFixedObstacles(){
        return this.fixedObstacles;
    }

    /** Ajoute un obstacle fixe */
    public void addFixedObstacle(Obstacle fixedObstacle){
        if (!this.fixedObstacles.contains(fixedObstacle)) {
            this.fixedObstacles.add(fixedObstacle);
        }
    }

    /** Renvoie les obstacles mobiles */
    public ArrayList<Obstacle> getMobileObstacles(){
        return this.mobileObstacles;
    }

    /** Ajoute un obstacle mobile */
    public void addMobileObstacle(Obstacle mobileObstacle){
        this.mobileObstacles.add(mobileObstacle);
    }
}
