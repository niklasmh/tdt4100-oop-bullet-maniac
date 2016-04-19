package game.components.core.shapes;

import game.components.core.LocalPoint;

/**
 * Created by niklasmh on 13.04.16.
 */
public class Rectangle extends Polygon {
    private double w, h;

    public Rectangle (double gx, double gy, double x, double y, double w, double h) {
        super(gx, gy,
                new LocalPoint(x, y),
                new LocalPoint(x + w, y),
                new LocalPoint(x + w, y + h),
                new LocalPoint(x, y + h));
        this.w = w;
        this.h = h;
        this.setType("Polygon");
    }

    public Rectangle (double gx, double gy, double x, double y, double w, double h, double rot) {
        super(gx, gy, rot,
                new LocalPoint(x, y),
                new LocalPoint(x + w, y),
                new LocalPoint(x + w, y + h),
                new LocalPoint(x, y + h));
        this.w = w;
        this.h = h;
        this.setType("Polygon");
    }

    public Rectangle (double gx, double gy, double x, double y, double w, double h, double rot, double scale) {
        super(gx, gy, rot, scale,
                new LocalPoint(x, y),
                new LocalPoint(x + w, y),
                new LocalPoint(x + w, y + h),
                new LocalPoint(x, y + h));
        this.w = w;
        this.h = h;
        this.setType("Polygon");
    }

    public double getW () {
        return this.w;
    }

    public double getH () {
        return this.h;
    }
}
