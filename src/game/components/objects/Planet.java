package game.components.objects;

import game.components.core.Body;
import game.components.core.LocalPoint;
import game.components.core.shapes.Polygon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Niklas on 4/17/2016.
 */
public class Planet extends Body {

    public Planet () {
        this(0, 0);
    }

    public Planet (double radius, double angle) {
        super(radius, angle);

        Random rand = new Random();
        List<LocalPoint> points = new ArrayList<>();

        for (double i = 0, s = 1337 / 14; i <= Math.PI; i += Math.PI / s * 2) {
            double r = rand.nextDouble() / s * 2 + 1 - 1 / s * 2;
            points.add(new LocalPoint(r * s * Math.sin(i), r * s * Math.cos(i)));
        }

        for (double i = Math.PI, s = 1200 / 13; i > 0; i -= Math.PI / s * 2) {
            double r = rand.nextDouble() / s * 2 + 1 - 1 / s * 2;
            points.add(new LocalPoint(r * s * Math.sin(i), r * s * Math.cos(i)));
        }

        Polygon track = new Polygon(0, 0);
        track.addPoints(points);

        this.addShape(track);
    }
}
