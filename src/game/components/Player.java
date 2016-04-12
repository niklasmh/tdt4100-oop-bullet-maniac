package game.components;

/**
 * Created by niklasmh on 12.04.16.
 */
public class Player extends GameNode {

    public Player () {
        super(100, 0);
    }

    public Player (double radius, double angle) {
        super(radius, angle);
    }

    /**
     * Change the position of the player.
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
}
