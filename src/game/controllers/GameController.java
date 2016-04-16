package game.controllers;

import game.CurrentScreen;
import game.components.core.Body;
import game.components.core.Shape;
import game.components.core.shapes.Circle;
import game.components.core.shapes.Rectangle;
import game.components.objects.Player;
import game.components.objects.ViewPoint;
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
                    ctx.setStroke(Color.BLACK);
                    ctx.setLineWidth(2);
                    drawBody(ctx, player, true);

                    ctx.fill();
                    ctx.stroke();
                }
            }
        }.start();
    }

    /**
     * Draw shape from body.
     */
    public void drawBody (Body body, boolean debugDraw) {
        drawBody(this.ctx, body, debugDraw);
    }

    /**
     * Draw shape from body.
     */
    public void drawBody (Body body) {
        drawBody(this.ctx, body, false);
    }

    /**
     * Draw shape from body.
     */
    public void drawBody (GraphicsContext gc, Body body) {
        drawBody(gc, body, false);
    }

    /**
     * Draw shape from body into specified canvas.
     */
    public void drawBody (GraphicsContext gc, Body body, boolean debugDraw) {
        double gx = body.getX();
        double gy = body.getY();
        double x, y, r, w, h;

        for (Shape shape : body.getShapes()) {
            x = shape.getX() + gx;
            y = shape.getY() + gy;

            switch (shape.getType()) {
                case "Circle":
                    Circle circ = (Circle) shape;
                    r = circ.getCircleRadius();

                    if (debug) {
                        gc.strokeOval(vpx(x, y) - vps(r) / 2, vpy(x, y) - vps(r) / 2, vps(r), vps(r));
                    } else {
                        gc.fillOval(vpx(x, y) - vps(r) / 2, vpy(x, y) - vps(r) / 2, vps(r), vps(r));
                    }

                    break;
                case "Rectangle":
                    Rectangle rect = (Rectangle) shape;
                    w = rect.getW();
                    h = rect.getH();

                    if (debug) {
                        gc.strokeLine(vpx(x, y), vpy(x + w, y), vpx(x + w, y), vpy(x + w, y + h));
                        gc.strokeLine(vpx(x + w, y), vpy(x + w, y + h), vpx(x + w, y + h), vpy(x, y + h));
                        gc.strokeLine(vpx(x + w, y + h), vpy(x, y + h), vpx(x, y + h), vpy(x, y));
                        gc.strokeLine(vpx(x, y + h), vpy(x, y), vpx(x, y), vpy(x + w, y));
                    } else {
                        double[] xP = new double[] {
                                vpx(x, y),
                                vpx(x + w, y),
                                vpx(x + w, y + h),
                                vpx(x, y + h)
                        };

                        double[] yP = new double[] {
                                vpy(x, y),
                                vpy(x + w, y),
                                vpy(x + w, y + h),
                                vpy(x, y + h)
                        };

                        gc.fillPolygon(xP, yP, 4);
                    }

                    break;
            }
        }

        if (debug) {
            gc.setStroke(Color.BLUE);
            gc.setLineWidth(2);
            gc.strokeOval(vpx(gx, gy) - vps(1) / 2, vpy(gx, gy) - vps(1) / 2, vps(1), vps(1));
        }
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
