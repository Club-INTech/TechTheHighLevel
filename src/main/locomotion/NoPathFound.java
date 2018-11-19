package locomotion;

import utils.math.Vec2;

/**
 * Exception levée lorsque le Pathfinding ne trouve pas de chemin
 * TODO 1As
 */
public class NoPathFound extends Exception {

    /**
     * Point auquel on veut aller
     */
    private Vec2 aim;

    /**
     * @param aim   point de visé du pathfinder
     */
    public NoPathFound(Vec2 aim) {
        this.aim = aim;
    }

    /**
     * Getter
     */
    public Vec2 getAim() {
        return aim;
    }
}
