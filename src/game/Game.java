package game;

import game.resources.Global;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.awt.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by niklasmh on 10.04.16.
 */
public class Game extends Application implements Global {

    public static HashMap<String, String> screens = new HashMap<>();
    public final static Point size = new Point(640, 480); // Default ratio for game
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



        scene.setOnKeyPressed(evt -> {
            if (!keyCodes.contains(evt.getCode().toString())) {
                System.out.println("Key pressed " + evt.getCode());
                keyCodes.add(evt.getCode().toString());
            }
        });
        scene.setOnKeyReleased(evt -> {
            if (keyCodes.contains(evt.getCode().toString())) {
                System.out.println("Key released " + evt.getCode());
                keyCodes.removeAll(Collections.singleton(evt.getCode().toString()));
            }
        });

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
                // Main game loop
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
        double scale = (minRatio > ratio ? scrH / size.getY() : scrW / size.getX());

        sceneRoot.setScaleX(scale);
        sceneRoot.setScaleY(scale);
        sceneRoot.setTranslateX((scrW - size.getX()) / 2);
        sceneRoot.setTranslateY((scrH - size.getY() - top) / 2);

        windowSize.setLocation(stg.getWidth() / scale, (stg.getHeight() - top) / scale);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
