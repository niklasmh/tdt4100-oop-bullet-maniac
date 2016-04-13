package game.components.objects;

import game.components.core.Body;

/**
 * Created by niklasmh on 12.04.16.
 */
public class Player extends Body {

    public Player () {
        super(100, 0);
    }

    public Player (double radius, double angle) {
        super(radius, angle);
    }
}
