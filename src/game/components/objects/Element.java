package game.components.objects;

import game.components.core.Body;
import game.components.core.shapes.Rectangle;

/**
 * Created by Niklas on 4/17/2016.
 */
public class Element extends Body {

    public Element () {
        this(0, 0);
    }

    public Element (double radius, double angle) {
        super(radius, angle);

        this.addShape(new Rectangle(Math.PI/2, 100, 0, 0, 100, 100));
    }
}
