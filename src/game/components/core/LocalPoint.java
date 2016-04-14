package game.components.core;

/**
 * Created by niklasmh on 13.04.16.
 */
public class LocalPoint {
    protected double x;
    protected double y;

    public LocalPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    protected void setPoint (double x, double y) {
        this.x = x;
        this.y = y;
    }

    protected void setX (double x) {
        this.x = x;
    }

    protected void setY (double y) {
        this.y = y;
    }

    protected double getX () {
        return this.x;
    }

    protected double getY () {
        return this.y;
    }
}
