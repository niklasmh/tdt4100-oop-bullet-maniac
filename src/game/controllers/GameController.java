package game.controllers;

import game.CurrentScreen;
import game.components.objects.Player;
import game.components.objects.ViewPoint;
import game.Global;
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
import java.util.Collections;
import java.util.ResourceBundle;

/**
 * Created by niklasmh on 10.04.16.
 */
public class GameController implements Initializable, CurrentScreen, Global {
    ScreenController scrCtrl;
    GraphicsContext ctx;
    ViewPoint viewPoint;
    @FXML Canvas canvas;
    @FXML VBox gameMenu;
    @FXML VBox gamePlay;
    boolean showMenu;
    double zoom = 1;

    @Override
    public void initialize (URL url, ResourceBundle src) {
        ctx = canvas.getGraphicsContext2D();
        showMenu(false);

        viewPoint = new ViewPoint(0, 0);
        Player player = new Player(100, 100);

        new AnimationTimer() {
            @Override
            public void handle (long time) {
                if (scrCtrl.getScreen().equals("game") && !showMenu) {

                    /**
                     * Game logic and controllers.
                     */
                    if (isKeyDown("LEFT")) {
                        player.increaseX(1);
                    }

                    if (isKeyDown("RIGHT")) {
                        player.increaseX(-1);
                    }

                    if (isKeyDown("UP")) {
                        player.increaseY(1);
                    }

                    if (isKeyDown("DOWN")) {
                        player.increaseY(-1);
                    }

                    /**
                     * First clearing the stage for the next frame.
                     */
                    canvas.setWidth(windowSize.getX());
                    canvas.setHeight(windowSize.getY());
                    ctx.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                    ctx.setFill(Color.GRAY);
                    ctx.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

                    /**
                     * Drawing the background.
                     */
                    ctx.setFill(Color.LIGHTGRAY);
                    ctx.setStroke(Color.DARKGRAY);
                    ctx.setLineWidth(10);
                    ctx.fillOval(vpx(0, 0) - vps(100) / 2, vpy(0, 0) - vps(100) / 2, vps(100), vps(100));

                    /**
                     * Drawing the player.
                     */
                    ctx.strokeOval(vpx(player.getX(), player.getY()) - vps(20) / 2, vpy(player.getX(), player.getY()) - vps(20) / 2, vps(20), vps(20));
                    ctx.strokeLine(vpx(0, 0), vpy(0, 0), vpx(player.getX(), player.getY()), vpy(player.getX(), player.getY()));
                }
            }
        }.start();
    }

    /**
     * Convert scaling to right scale relative to screen.
     * VPX = View Point Scale
     */
    public double vps(double val) {
        return val * this.zoom;
    }

    /**
     * Convert all points relative to ViewPoint.
     * VPX = View Point X
     */
    public double vpx (double x, double y) {
        double posX = y * Math.sin(x);
        return posX - this.viewPoint.getX() + canvas.getWidth() / 2;
    }

    /**
     * Convert all points relative to ViewPoint.
     * VPX = View Point X
     */
    public double vpy (double x, double y) {
        double posY = y * Math.cos(x);
        return posY - this.viewPoint.getY() + canvas.getHeight() / 2;
    }

    /**
     * Checks different key combinations. Just to make the
     * game more modular and possible to play with
     * different key combinations.
     *
     * @param key = string describing the key action
     * @return = true if key combination is true
     */
    public boolean isKeyDown (String key) {
        switch (key) {
            case "LEFT":
                return keyCodes.contains("LEFT") || keyCodes.contains("A");
            case "RIGHT":
                return keyCodes.contains("RIGHT") || keyCodes.contains("D");
            case "UP":
                return keyCodes.contains("UP") || keyCodes.contains("W");
            case "DOWN":
                return keyCodes.contains("DOWN") || keyCodes.contains("S");
            default:
                return false;
        }
    }

    /**
     * Removes key pressed from another list when noticed.
     * Nice to use when key only should be used one time in the loop.
     *
     * @param keyCode = string, like "LEFT", "A" etc...
     */
    public boolean removeKeyPressed (String keyCode) {
        if (keyPressedCodes.contains(keyCode)) {
            keyPressedCodes.removeAll(Collections.singleton(keyCode));
            return true;
        }

        return false;
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
