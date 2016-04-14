package game.components.core.shapes;

import game.components.core.Shape;

/**
 * Created by niklasmh on 13.04.16.
 */
public class Circle extends Shape {
    private double circleRadius;

    public Circle (double x, double y, double r) {
        super(x, y);
        this.circleRadius = r;
        this.setType("Circle");
    }

    public Circle (double x, double y, double r, double rot) {
        super(x, y, rot);
        this.circleRadius = r;
        this.setType("Circle");
    }

    public Circle (double x, double y, double r, double rot, double scale) {
        super(x, y, rot, scale);
        this.circleRadius = r;
        this.setType("Circle");
    }

    public double getCircleRadius () {
        return this.circleRadius;
    }
}
