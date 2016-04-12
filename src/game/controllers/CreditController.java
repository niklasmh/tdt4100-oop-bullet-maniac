package game.controllers;

import game.CurrentScreen;
import game.ScreenController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by niklasmh on 12.04.16.
 */
public class CreditController implements Initializable, CurrentScreen {
    ScreenController scrCtrl;

    @Override
    public void initialize (URL url, ResourceBundle src) {
        // Code to initialize at start of screen
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
        this.scrCtrl.setScreen("menu");
    }
}
