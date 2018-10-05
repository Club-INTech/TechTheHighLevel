package locomotion;

import data.table.Table;
import utils.math.Vec2;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Pathfinding {

    private Table table;
    private ConcurrentLinkedQueue<Vec2> path;

    Pathfinding(Table table){
        this.table=table;
        this.path=new ConcurrentLinkedQueue<Vec2>();
    }

    /** Permet de trouver ue chemin vers une position donnée */
    ConcurrentLinkedQueue<Vec2> findPath(Vec2 targetPosition){
        //C'est aux 1AS de faire ça !
        return null;
    }





    /** Permet de savoir si, dans le chemin prévu, il y a une position cible qui n'est pas en train d'être atteinte*/
    boolean doesNextNodeExist(){
        return (this.path.peek()!=null);
    }
    /** Permet d'avoir la prochaine position vers laquelle le robot doit se déplacer */
    Vec2 getNextNode(){
        if (this.path.peek() != null){
            return this.path.poll();
        }
        else{
            return null;
        }
    }
    /** Permet de faire oublier au robot le chemin prévu*/
    void clearPath(){
        this.path.clear();
    }
    /** Permet d'ajouter une étape à la fin du chemin prévu*/
    void addPositionToPath(Vec2 position){
        this.path.add(position);
    }
    /** Permet de remplacer le chemin prévu actuel par un autre chemin prévu*/
    void setPath(ConcurrentLinkedQueue<Vec2> path){
        this.path=path;
    }
}
