package game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by niklasmh on 10.04.16.
 */
public class Game extends Application {

    public static HashMap<String, String> screens = new HashMap<>();
    public static Point size = new Point(640, 480); // Default ratio for game
    public static Parent sceneRoot;

    @Override
    public void start(Stage stg) throws Exception {
        Game.screens.put("menu", "menu.fxml");
        Game.screens.put("credit", "credit.fxml");
        Game.screens.put("game", "game.fxml");

        ScreenController scrCtrl = new ScreenController();

        for (Map.Entry<String, String> screen : Game.screens.entrySet()) {
            scrCtrl.loadScreen(screen.getKey(), "views/" + screen.getValue());
        }

        scrCtrl.setScreen("menu");

        Group root = new Group();
        root.getChildren().addAll(scrCtrl);

        Scene scene = new Scene(root, size.getX(), size.getY());
        scene.getStylesheets().add("game/resources/main.css");
        scene.widthProperty().addListener(obVal -> updateWindowSize(stg, scene));
        scene.heightProperty().addListener(obVal -> updateWindowSize(stg, scene));

        stg.setScene(scene);
        sceneRoot = scene.getRoot();
        stg.setTitle("Bullet Maniac");
        //stg.initStyle(StageStyle.TRANSPARENT); // Hiding the window border
        stg.show();

        /**
         * When closing the application will save the state before closing.
         */
        stg.setOnCloseRequest((WindowEvent event) -> {
            System.out.println("Bye..");
            Platform.exit();
        });

        /**
         * Need to be called at the start.
         */
        updateWindowSize(stg, scene);

        /**
         * The main game loop. This needs to run all the time
         * as this class controls all the controllers and views.
         */
        new AnimationTimer() {
            public void handle(long currentTime) {
                // Game loop
                updateWindowSize(stg, scene);
            }
        }.start();
    }

    /**
     * Updates and scales the game perfect into the screen.
     * The main aspect ratio is kept while all resolutions
     * are supported.
     *
     * @param stg = the main stage
     */
    public void updateWindowSize(Stage stg, Scene scn) {
        double top = scn.getY();
        double scrW = stg.getWidth();
        double scrH = stg.getHeight();
        double ratio = scrW / scrH;
        double minRatio = size.getX() / size.getY();

        if (minRatio > ratio) {
            sceneRoot.setScaleX(scrH / size.getY());
            sceneRoot.setScaleY(scrH / size.getY());
            sceneRoot.setTranslateX((scrW - size.getX()) / 2);
            sceneRoot.setTranslateY((scrH - size.getY() - top) / 2);
        } else {
            sceneRoot.setScaleX(scrW / size.getX());
            sceneRoot.setScaleY(scrW / size.getX());
            sceneRoot.setTranslateX((scrW - size.getX()) / 2);
            sceneRoot.setTranslateY((scrH - size.getY() - top) / 2);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
