package data.Obstacle;

public class Obstacle {

    private int x;
    private int y;

    Obstacle(int x, int y){
        this.x=x;
        this.y=y;
    }

    protected void setPosition(int x, int y){
        this.x=x;
        this.y=y;
    }
}
