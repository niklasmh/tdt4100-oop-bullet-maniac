package game.components.core;

import game.components.shapes.Shape;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by niklasmh on 13.04.16.
 */
public class Body extends GameNode {
    List<Shape> shapes = new ArrayList<>();

    public Body () {
        super(0, 0);
    }

    public Body (double radius, double angle, Shape... shapes) {
        super(radius, angle);
        Collections.addAll(this.shapes, shapes);
    }

    public void addShape (Shape... shapes) {
        Collections.addAll(this.shapes, shapes);
    }
}
