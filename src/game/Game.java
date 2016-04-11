package game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by niklasmh on 10.04.16.
 */
public class Game extends Application {

    public static HashMap<String, String> screens = new HashMap<>();
    public static Point size = new Point(640, 480);
    public static Point minSize = new Point(640, 480);
    public static Parent sceneRoot;

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
        Scene scene = new Scene(root, size.getX(), size.getY());
        scene.getStylesheets().add("game/resources/main.css");
        stg.setScene(scene);
        sceneRoot = scene.getRoot();
        stg.setTitle("Bullet Maniac");
        stg.show();

        new AnimationTimer() {
            public void handle(long currentTime) {
                double scrW = stg.getWidth();
                double scrH = stg.getHeight();
                double ratio = scrW / scrH;
                double minRatio = minSize.getX() / minSize.getY();

                if (minRatio > ratio) {
                    sceneRoot.setScaleX(scrH / size.getY());
                    sceneRoot.setScaleY(scrH / size.getY());
                    sceneRoot.setTranslateX((scrW - size.getX()) / 2);
                    sceneRoot.setTranslateY((scrH - size.getY()) / 2);
                } else {
                    sceneRoot.setScaleX(scrW / size.getX());
                    sceneRoot.setScaleY(scrW / size.getX());
                    sceneRoot.setTranslateX((scrW - size.getX()) / 2);
                    sceneRoot.setTranslateY((scrH - size.getY()) / 2);
                }
            }
        }.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
