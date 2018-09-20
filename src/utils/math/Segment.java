package utils.math;

public class Segment {

    /**Premier point du segment*/
    private Vec2 pointA;

    /**Deuxième point du segment*/
    private Vec2 pointB;

    /**Constructeur*/
    public Segment(Vec2 pointA, Vec2 pointB) {
        this.pointA = pointA;
        this.pointB = pointB;
    }

    /**cette méthode détermine si deux droites sont parallèles ou non*/
    public boolean intersectsWithSegment(Segment segment){
        return false;
    }

    /**Cette méthode détermine la distance entre une droite et un point*/
    public float distanceToPoint(Vec2 point){
        return 0;
    }

    /**Cette méthode retourne la longueur d'un segment*/
    public float longueurSegment(){
        return 0;
    }

    /**Cette méthode détermine le vecteur directeur d'une droite*/
    public Vec2 vecteurDirecteur(){
        return null;
    }
}
