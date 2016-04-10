package game.controllers;

import game.CurrentScreen;
import game.ScreenController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by niklasmh on 10.04.16.
 */
public class GameController implements Initializable, CurrentScreen {
    ScreenController scrCtrl;

    @Override
    public void initialize (URL url, ResourceBundle src) {
        // Code to initialize at start of screen
    }

    /**
     *  Sets the screen to screen controller.
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
