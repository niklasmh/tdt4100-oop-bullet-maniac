package game.controllers;

import game.CurrentScreen;
import game.Global;
import game.ScreenController;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

/**
 * Created by niklasmh on 10.04.16.
 */
public class MenuController implements Initializable, CurrentScreen, Global {
    ScreenController scrCtrl;

    @Override
    public void initialize (URL url, ResourceBundle src) {
        new AnimationTimer() {
            @Override
            public void handle(long time) {
                if (scrCtrl.getScreen().equals("menu")) {

                    /**
                     * Making back space go back to menu.
                     */

                    if (isKeyPressed("ENTER")) {
                        goToGame();
                    }

                    if (isKeyPressed("I")) {
                        goToCredit();
                    }

                    if (isKeyPressed("Q")) {
                        Platform.exit();
                    }

                    removeKeysPressed();
                }
            }
        }.start();
    }

    /**
     * Checks if key is pressed.
     *
     * @param key
     * @return
     */
    public boolean isKeyPressed (String key) {
        boolean pressed = keyPressedCodes.contains(key);
        keyPressedCodes.removeAll(Collections.singleton(key));
        return pressed;
    }

    /**
     * Remove all keys pressed.
     */
    public void removeKeysPressed () {
        keyPressedCodes.clear();
    }

    /**
     * Sets the screen to screen controller.
     *
     * @param screen = screen controller
     */
    public void setScreen(ScreenController screen) {
        this.scrCtrl = screen;
    }

    @FXML
    public void goToGame (ActionEvent evt) {
        goToGame();
    }

    public void goToGame () {
        this.scrCtrl.setScreen("game");
    }

    @FXML
    public void goToCredit (ActionEvent evt) {
        goToCredit();
    }

    public void goToCredit () {
        this.scrCtrl.setScreen("credit");
    }

    @FXML
    public void windowClose (ActionEvent evt) {
        Platform.exit();
    }
}
