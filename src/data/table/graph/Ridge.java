package data.table.graph;

import utils.math.Segment;
import utils.math.Vec2;

/** Arête composant le graphe */
public class Ridge {

    private Segment segment;
    private int cost = 0; //Cout de l'arête (utile et mis à jour lors de l'exécution du pathfinding)
    private int staticCost = 0; //Cout statique de l'arête
    private boolean usable; //Si on peut emprunter cette arête

    /** Constructeur */
    public Ridge(Segment segment){
        this.segment=segment;
    }

    /** Constructeur avec Vec2 */
    public Ridge(Vec2 firstPoint, Vec2 secondPoint){
        this.segment=new Segment(firstPoint,secondPoint);
    }

    /** Renvoie le segment */
    public Segment getSegment(){
        return this.segment;
    }

    /** Définit si l'arête est utilisable */
    public void setUsable(boolean usable) {
        this.usable = usable;
    }

    /** Renvoie si l'arête est utilisable */
    public boolean isUsable() {
        return this.usable;
    }

    @Override
    /** Renvoie si l'arête a les mêmes coordonnées de départ et de fin que l'arête donnée en argument
     * (Ne tient pas compte de l'ordre des points) */
    public boolean equals(Object obj) {
        if (obj instanceof Ridge){
            Vec2 pointA = ((Ridge) obj).getSegment().getPointA();
            Vec2 pointB = ((Ridge) obj).getSegment().getPointB();
            if (this.segment.getPointA() == pointA){
                return (this.segment.getPointB() == pointB);
            }
            else if (this.segment.getPointA() == pointB){
                return (this.segment.getPointB() == pointA);
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    @Override
    /** Renvoie les coordonnées des deux points composant l'arête */
    public String toString() {
        return this.segment.toString();
    }
}
