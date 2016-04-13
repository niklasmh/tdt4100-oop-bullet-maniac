package game.components.core.shapes;

import game.components.core.Shape;

/**
 * Created by niklasmh on 13.04.16.
 */
public class Polygon extends Shape {
    private double radius;

    public Polygon (double x, double y, double r) {
        super(x, y);
        this.radius = r;
    }

    public Polygon (double x, double y, double r, double rot) {
        super(x, y, rot);
        this.radius = r;
    }

    public Polygon (double x, double y, double r, double rot, double scale) {
        super(x, y, rot, scale);
        this.radius = r;
    }
}
