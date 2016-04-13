package game.components.core;

/**
 * Created by niklasmh on 12.04.16.
 *
 * This class is needed as the game uses polar coordinates.
 */
public class PolarPoint {
    protected double radius;
    protected double angle;

    public PolarPoint(double radius, double angle) {
        this.radius = radius;
        this.angle = angle;
    }

    protected void setPoint (double radius, double angle) {
        this.radius = radius;
        this.angle = angle;
    }

    protected void setRadius (double radius) {
        this.radius = radius;
    }

    protected void setAngle (double angle) {
        this.angle = angle;
    }

    protected double getRadius () {
        return this.radius;
    }

    protected double getAngle () {
        return this.angle;
    }
}
