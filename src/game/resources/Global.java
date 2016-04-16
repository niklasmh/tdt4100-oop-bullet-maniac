package game.resources;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by niklasmh on 12.04.16.
 */
public interface Global {
    Point windowSize = new Point(640, 480);
    List<String> keyCodes = new ArrayList<>();
    List<String> keyPressedCodes = new ArrayList<>();
    boolean debug = true;
}
