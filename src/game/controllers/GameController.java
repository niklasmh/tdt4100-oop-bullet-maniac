package game.controllers;

import game.CurrentScreen;
import game.resources.Global;
import game.ScreenController;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by niklasmh on 10.04.16.
 */
public class GameController implements Initializable, CurrentScreen, Global {
    ScreenController scrCtrl;
    GraphicsContext ctx;
    @FXML Canvas canvas;
    @FXML VBox gameMenu;
    @FXML VBox gamePlay;
    boolean showMenu;

    @Override
    public void initialize (URL url, ResourceBundle src) {
        ctx = canvas.getGraphicsContext2D();

        showMenu(false);

        new AnimationTimer() {
            @Override
            public void handle (long time) {
                if (scrCtrl.getScreen().equals("game") && !showMenu) {

                    canvas.setWidth(windowSize.getX());
                    canvas.setHeight(windowSize.getY());
                    System.out.println(windowSize.getX());

                    ctx.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                    ctx.setFill(Color.GRAY);
                    ctx.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

                    ctx.setStroke(Color.LIME);
                    ctx.moveTo(0, 0);
                    ctx.lineTo(Math.random() * 200, Math.random() * 200);
                    ctx.moveTo(windowSize.getX(), windowSize.getY());
                    ctx.lineTo(Math.random() * 200, Math.random() * 200);
                    ctx.stroke();
                }
            }
        }.start();
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
    public void goToMainMenu (ActionEvent evt) {
        this.resetGame();
        this.scrCtrl.setScreen("menu");
    }

    @FXML
    public void toggleMenu (ActionEvent evt) {
        toggleMenu();
    }

    public void resetGame () {
        showMenu(false);
        ctx.clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
    }

    public void toggleMenu () {
        showMenu(!this.showMenu);
    }

    public void showMenu () {
        showMenu(true);
    }

    /**
     * Shows or hides menu in the game. his will also
     * control the timer in the game.
     *
     * @param show = boolean for showing or hiding
     */
    public void showMenu (boolean show) {
        this.showMenu = show;

        gameMenu.setVisible(show);
        gameMenu.managedProperty().bind(gameMenu.visibleProperty());

        gamePlay.setVisible(!show);
        gamePlay.managedProperty().bind(gamePlay.visibleProperty());
    }
}
