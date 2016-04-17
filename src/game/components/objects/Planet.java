package game.components.objects;

import game.components.core.Body;
import game.components.core.LocalPoint;
import game.components.core.shapes.Polygon;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niklas on 4/17/2016.
 */
public class Planet extends Body {

    public Planet () {
        this(0, 0);
    }

    public Planet (double radius, double angle) {
        super(radius, angle);

        List<LocalPoint> points = new ArrayList<>();
        for (double i = 0, s = 100; i <= Math.PI ; i += Math.PI / 10.0) {
            points.add(new LocalPoint(s * Math.sin(i), s * Math.cos(i)));
        }

        Polygon track = new Polygon(0, 0);
        track.addPoints(points);

        this.addShape(track);
    }
}
