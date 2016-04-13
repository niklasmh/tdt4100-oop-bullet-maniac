package game.components;

import java.awt.Point;

/**
 * Created by niklasmh on 12.04.16.
 */
public class GameNode extends PolarPoint {

    public GameNode (double x, double y) {
        super(x, y);
    }

    /**
     * Change the position of the viewpoint.
     *
     * @param radius
     * @param angle
     */
    public void setPosition (double radius, double angle) {
        this.setPoint(radius, angle);
    }

    /**
     * Get the radius from core of the planet.
     * This is always correct.
     *
     * @return
     */
    public double getY () {
        return this.getRadius();
    }

    /**
     * Get the real scale x position on the planet.
     *
     * @return = x pos in double
     */
    public double getX () {
        return this.getAngle() * this.getRadius();
    }

    public void increaseX (double x) {
        setAngle(getAngle() + x / getRadius());
    }

    public void increaseY (double y) {
        setRadius(getRadius() + y);
    }
}
