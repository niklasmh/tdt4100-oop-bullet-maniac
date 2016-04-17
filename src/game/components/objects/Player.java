package game.components.objects;

import game.components.core.Body;
import game.components.core.LocalPoint;
import game.components.core.shapes.Circle;
import game.components.core.shapes.Polygon;
import game.components.core.shapes.Rectangle;

/**
 * Created by niklasmh on 12.04.16.
 */
public class Player extends Body {
    private double speedX, speedY, velX, velY;
    private final double maxValX = 3, maxValY = 1000;

    public Player () {
        this(1337 + 40, 0);
    }

    public Player (double radius, double angle) {
        super(radius, angle);

        this.speedX = 0;
        this.speedY = 0;
        this.velX = 0;
        this.velY = 0;

        this.addShape(new Circle(0, 0, 20));
    }

    public void update () {
        this.speedX += velX;
        this.speedX = Math.min(Math.max(this.speedX, -this.maxValX), this.maxValX);
        this.speedX *= .9;
        this.increaseX(this.speedX);
        this.setVelX(0);

        this.speedY += velY;
        this.speedY = Math.min(Math.max(this.speedY, -this.maxValY), this.maxValY);
        this.speedY *= .95;
        if (this.getY() <= 1337 / 2 + 10 && velY < 0) {
            this.speedY = 0;
        }
        this.increaseY(this.speedY);
        this.setVelY(-.1);
    }

    public void addVelX (double x) {
        this.velX += x;
    }

    public void addVelY (double y) {
        this.velY += y;
    }

    public void setVelX (double x) {
        this.velX = x;
    }

    public void setVelY (double y) {
        this.velY = y;
    }

    public double getVelX () {
        return this.velX;
    }

    public double getVelY () {
        return this.velY;
    }

    public double getSpeedX () {
        return this.speedX;
    }

    public double getSpeedY () {
        return this.speedY;
    }
}
