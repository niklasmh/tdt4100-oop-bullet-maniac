package game;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import java.util.HashMap;

/**
 * Created by niklasmh on 10.04.16.
 */
public class ScreenController extends StackPane {

    /**
     * The screens are saved in a hash map to be easily accessed.
     */
    private HashMap<String, Node> screens = new HashMap<>();

    /**
     * Extends StackPane, so needs to call super to call StackPanes' constructor.
     */
    public ScreenController () {
        super();
    }

    /**
     * Adding screen node to map tagged with an id.
     *
     * @param id = unique identification as string
     * @param screen = screen node
     */
    public void addScreen(String id, Node screen) {
        screens.put(id, screen);
    }

    /**
     * Returns a screen node from hash map at id.
     *
     * @param id = the unique as string
     * @return = screen node
     */
    public Node getScreen(String id) {
        return screens.get(id);
    }

    /**
     * Error handling when fetching a new screen. Returns true if success.
     *
     * @param id = the unique hash map string
     * @param src = the source file to load FXML from
     * @return = true if success
     */
    public boolean loadScreen(String id, String src) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(src));
            Parent loadScreen = (Parent)loader.load();
            CurrentScreen currScreen = (CurrentScreen)loader.getController();
            currScreen.setScreen(this);
            addScreen(id, loadScreen);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Replaces the current id in the display map with a new one.
     * Only one screen node should be in the getChildren list.
     *
     * @param id = the unique id for screen
     * @return = true if the unique id exists
     */
    public boolean setScreen(final String id) {
        if (screens.get(id) != null) {
            if (!getChildren().isEmpty()) {
                getChildren().remove(0);
            }

            getChildren().add(0, screens.get(id));

            return true;
        }

        return false;
    }

    /**
     * Unloads the screen from id.
     *
     * @param id = unique id
     * @return = true if the id got removed from the hash map
     */
    public boolean unloadScreen(String id) {
        return (screens.remove(id) != null);
    }
}
