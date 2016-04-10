package game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by niklasmh on 10.04.16.
 */
public class Main extends Application {

    private HashMap<String, String> screens = new HashMap<>();

    @Override
    public void start(Stage stg) throws Exception {
        screens.put("menu", "menu.fxml");

        ScreenController scrCtrl = new ScreenController();

        for (Map.Entry<String, String> screen: screens.entrySet()) {
            scrCtrl.loadScreen(screen.getKey(), screen.getValue());
        }

        scrCtrl.setScreen("menu");

        Group root = new Group();
        root.getChildren().addAll(scrCtrl);
        stg.setScene(new Scene(root, 300, 275));
        stg.setTitle("Bullet Maniac");
        stg.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
