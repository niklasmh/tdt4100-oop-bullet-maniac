package game.components.objects;

import game.components.core.Body;
import game.components.core.LocalPoint;
import game.components.core.shapes.Circle;
import game.components.core.shapes.Polygon;
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

        this.addShape(new Circle(10, 10, 20),
                new Rectangle(0, 0, 100, 40),
                new Rectangle(0, 0, 10, 10),
                new Polygon(0, 0, new LocalPoint(0, 0), new LocalPoint(100, 100), new LocalPoint(50, 10)));
    }
}
