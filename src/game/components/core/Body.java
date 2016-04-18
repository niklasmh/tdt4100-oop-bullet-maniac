package game.components.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by niklasmh on 13.04.16.
 */
public class Body extends GameNode {
    private List<Shape> shapes = new ArrayList<>();

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

    public List<Shape> getShapes () {
        return this.shapes;
    }

    public double distPointToLine (double px, double py, double x1, double y1, double x2, double y2) {
        double a = y2 - y1;
        double b = -(x2 - x1);
        double c = x2 * y1 - y2 * x1;
        return Math.abs(a * px + b * py + c) / Math.sqrt(a * a + b * b);
    }
}
