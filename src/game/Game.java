package game;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by niklasmh on 10.04.16.
 */
public class Game extends Application {

    public static HashMap<String, String> screens = new HashMap<>();

    @Override
    public void start(Stage stg) throws Exception {
        Game.screens.put("menu", "menu.fxml");
        Game.screens.put("game", "game.fxml");

        ScreenController scrCtrl = new ScreenController();

        for (Map.Entry<String, String> screen : Game.screens.entrySet()) {
            scrCtrl.loadScreen(screen.getKey(), "views/" + screen.getValue());
        }

        scrCtrl.setScreen("menu");

        Group root = new Group();
        root.getChildren().addAll(scrCtrl);
        Scene scene = new Scene(root, 300, 275);
        scene.getStylesheets().add("game/resources/main.css");
        stg.setScene(scene);
        stg.setTitle("Bullet Maniac");
        stg.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
