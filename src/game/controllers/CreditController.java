package game.controllers;

import game.CurrentScreen;
import game.Global;
import game.ScreenController;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

/**
 * Created by niklasmh on 12.04.16.
 */
public class CreditController implements Initializable, CurrentScreen, Global {
    ScreenController scrCtrl;

    @Override
    public void initialize (URL url, ResourceBundle src) {
        new AnimationTimer() {
            @Override
            public void handle(long time) {
                if (scrCtrl.getScreen().equals("credit")) {

                    /**
                     * Making back space go back to menu.
                     */

                    if (isKeyPressed("BACK_SPACE")) {
                        goToMenu();
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
    public void goToMenu (ActionEvent evt) {
        goToMenu();
    }

    public void goToMenu () {
        this.scrCtrl.setScreen("menu");
    }
}
