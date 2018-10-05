package data.table.graphe;

import utils.math.Segment;
import utils.math.Vec2;

public class Ridge {

    private Segment segment;
    private int cost;
    private int staticCost = 0;
    private boolean usable;

    public Ridge(Segment segment){
        this.segment=segment;
    }

    public Ridge(Vec2 firstPoint, Vec2 secondPoint){
        this.segment=new Segment(firstPoint,secondPoint);
    }

    public Segment getSegment(){
        return this.segment;
    }

    public void setUsable(boolean usable) {
        this.usable = usable;
    }

    public boolean getUsable() {
        return this.usable;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Ridge){
            if (obj.hashCode()==this.hashCode()){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
