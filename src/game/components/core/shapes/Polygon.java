package game.components.core.shapes;

import game.components.core.LocalPoint;
import game.components.core.Shape;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by niklasmh on 13.04.16.
 */
public class Polygon extends Shape {
    private List<LocalPoint> points = new ArrayList<>();

    public Polygon (double x, double y, LocalPoint... points) {
        super(x, y);
        Collections.addAll(this.points, points);
        this.setType("Polygon");
    }

    public Polygon (double x, double y, double rot, LocalPoint... points) {
        super(x, y, rot);
        Collections.addAll(this.points, points);
        this.setType("Polygon");
    }

    public Polygon (double x, double y, double rot, double scale, LocalPoint... points) {
        super(x, y, rot, scale);
        Collections.addAll(this.points, points);
        this.setType("Polygon");
    }

    public List<LocalPoint> getPoints () {
        return this.points;
    }

    public void addPoints (LocalPoint... points) {
        Collections.addAll(this.points, points);
    }

    public void addPoint (LocalPoint point) {
        this.points.add(point);
    }
}
