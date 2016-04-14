package game.components.objects;

import game.components.core.Body;
import game.components.core.shapes.Circle;
import game.components.core.shapes.Rectangle;

/**
 * Created by niklasmh on 12.04.16.
 */
public class Player extends Body {

    public Player () {
        this(100, 0);
    }

    public Player (double radius, double angle) {
        super(radius, angle);

        this.addShape(new Circle(0, 0, 20), new Rectangle(0, 0, 100, 40));
    }
}
