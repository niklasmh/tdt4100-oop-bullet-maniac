package game.components.core.shapes;

import game.components.core.LocalPoint;

/**
 * Created by niklasmh on 13.04.16.
 */
public class Rectangle extends Polygon {
    private double w, h;

    public Rectangle (double x, double y, double w, double h) {
        super(x, y,
                new LocalPoint(x + w, y),
                new LocalPoint(x + w, y + h),
                new LocalPoint(x, y + h));
        this.w = w;
        this.h = h;
        this.setType("Rectangle");
    }

    public Rectangle (double x, double y, double w, double h, double rot) {
        super(x, y, rot,
                new LocalPoint(x + w, y),
                new LocalPoint(x + w, y + h),
                new LocalPoint(x, y + h));
        this.w = w;
        this.h = h;
        this.setType("Rectangle");
    }

    public Rectangle (double x, double y, double w, double h, double rot, double scale) {
        super(x, y, rot, scale,
                new LocalPoint(x + w, y),
                new LocalPoint(x + w, y + h),
                new LocalPoint(x, y + h));
        this.w = w;
        this.h = h;
        this.setType("Rectangle");
    }

    public double getW () {
        return this.w;
    }

    public double getH () {
        return this.h;
    }
}
