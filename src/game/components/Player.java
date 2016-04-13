package game.components;

/**
 * Created by niklasmh on 12.04.16.
 */
public class Player extends GameNode {

    public Player () {
        super(100, 0);
    }

    public Player (double radius, double angle) {
        super(radius, angle);
    }
}
