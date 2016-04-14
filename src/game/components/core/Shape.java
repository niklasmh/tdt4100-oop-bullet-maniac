package game.components.core;

import game.components.core.GameNode;

/**
 * Created by niklasmh on 13.04.16.
 */
public abstract class Shape extends GameNode {
    private double scale;
    private boolean picture;
    private String type;

    public Shape (double x, double y) {
        super(x, y);
        this.scale = 1;
    }

    public Shape (double x, double y, double rot) {
        super(x, y, rot);
        this.scale = 1;
    }

    public Shape (double x, double y, double rot, double scale) {
        super(x, y, rot);
        this.scale = scale;
    }

    public String getType () {
        return this.type;
    }

    public void setType (String type) {
        this.type = type;
    }

    public double getScale () {
        return this.scale;
    }
}
