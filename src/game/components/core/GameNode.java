package game.components.core;

/**
 * Created by niklasmh on 12.04.16.
 */
public abstract class GameNode extends PolarPoint {
    double rotation;

    public GameNode (double x, double y) {
        super(x, y);
    }

    public GameNode (double x, double y, double rot) {
        super(x, y);
        this.rotation = rot;
    }

    /**
     * Change the position of the viewpoint.
     *
     * @param radius = double
     * @param angle = double
     */
    public void setPosition (double radius, double angle) {
        this.setPoint(radius, angle);
    }

    /**
     * Get the radius from core of the planet.
     * This is always correct.
     *
     * @return = radius as double
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
        return this.getAngle();
    }

    /**
     * Get the local rotation of the node.
     *
     * @return = angle in radians in double format
     */
    public double getRotation () {
        return this.rotation;
    }

    /**
     * Get the local rotation of the node.
     *
     * @return = angle in radians in double format
     */
    public void setRotation (double rot) {
        this.rotation = rot;
    }

    /**
     * Increases the x position based on radius from the center of radius.
     *
     * @param x = double
     */
    public void increaseX (double x) {
        setAngle(getAngle() + x / getRadius());
    }

    /**
     * Increases the y position relative to center of radius.
     *
     * @param y = double
     */
    public void increaseY (double y) {
        setRadius(getRadius() + y);
    }
}
