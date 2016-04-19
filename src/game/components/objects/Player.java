package game.components.objects;

import game.components.core.Body;
import game.components.core.LocalPoint;
import game.components.core.Shape;
import game.components.core.shapes.Circle;
import game.components.core.shapes.Polygon;
import game.components.core.shapes.Rectangle;

/**
 * Created by niklasmh on 12.04.16.
 */
public class Player extends Body {
    private double speedX, speedY, velX, velY;
    private final double maxValX = 3, maxValY = 1000;
    private int maxJumps = 3, jumps = 0;

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

        if (this.getY() <= 1337 / 137 + 10 && velY < 0) {
            this.speedY = 0;
            this.jumps = 0;
        }

        this.setVelY(-.1);
        this.increaseY(this.speedY);
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

    public void setSpeedX (double x) {
        this.speedX = x;
    }

    public void setSpeedY (double y) {
        this.speedY = y;
    }

    public double getSpeedX () {
        return this.speedX;
    }

    public double getSpeedY () {
        return this.speedY;
    }

    public int getJumpsLeft () {
        return this.maxJumps - this.jumps;
    }

    public void jump () {
        if (this.getJumpsLeft() > 0) {
            this.setVelY(6);
            this.jumps++;
        }
    }

    public void collision (Body... bodies) {

        double x = 0, y = 100, x1 = -50, y1 = 100, x2 = 50, y2 = 0, lrad;
        double ang = - Math.atan2(y2 - y1, x2 - x1) - this.getX() + x;

        //double r = this.distPointToLine(this.getX(), this.getY(), x1, y1, x2, y2);
        //System.out.println(this.closestPointFromPointToLine(this.getX(), this.getY(), x1, y1, x2, y2, 0) + " - " + r);

        double posX = y * Math.sin(x);
        double posY = y * Math.cos(x);

        lrad = Math.atan2(y1, x1) - x;
        double px1 = posX + Math.sqrt(x1 * x1 + y1 * y1) * Math.cos(lrad);
        double py1 = posY + Math.sqrt(x1 * x1 + y1 * y1) * Math.sin(lrad);

        lrad = Math.atan2(y2, x2) - x;
        double px2 = posX + Math.sqrt(x2 * x2 + y2 * y2) * Math.cos(lrad);
        double py2 = posY + Math.sqrt(x2 * x2 + y2 * y2) * Math.sin(lrad);

        double rx = this.intersectionBetweenLines(px1, py1, px2, py2, 0, 0, 1000 * Math.sin(this.getX()), 1000 * Math.cos(this.getX()), 0);
        double ry = this.intersectionBetweenLines(px1, py1, px2, py2, 0, 0, 1000 * Math.sin(this.getX()), 1000 * Math.cos(this.getX()), 1);
        double dist = Math.sqrt(rx*rx + ry*ry) - this.getY();

        if (!Double.isNaN(dist)) {
            if (dist < ((Circle) this.getShapes().get(0)).getCircleRadius() / 2 && dist > 0) {
                this.setAngle(this.getAngle() + (dist - ((Circle) this.getShapes().get(0)).getCircleRadius() / 2) * Math.sin(ang) / 500);
                this.setRadius(this.getRadius() + (dist - ((Circle) this.getShapes().get(0)).getCircleRadius() / 2) * Math.cos(ang));
            } else if (dist > -((Circle) this.getShapes().get(0)).getCircleRadius() / 2 && dist < 0) {
                this.setAngle(this.getAngle() + (dist + ((Circle) this.getShapes().get(0)).getCircleRadius() / 2) * Math.sin(ang) / 500);
                this.setRadius(this.getRadius() + (dist + ((Circle) this.getShapes().get(0)).getCircleRadius() / 2) * Math.cos(ang));
            }
            //this.addVelY(-this.getVelY());
            this.jumps = 0;
        }

        for (Body body : bodies) {
            for (Shape shape : body.getShapes()) {
                switch (shape.getType()) {
                    case "Polygon":
                        Polygon poly = (Polygon) shape;

                        for (LocalPoint point : poly.getPoints()) {

                        }
                        break;
                }
            }
        }
    }
}
