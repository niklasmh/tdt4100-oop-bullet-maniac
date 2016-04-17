package game.controllers;

import game.CurrentScreen;
import game.components.core.Body;
import game.components.core.LocalPoint;
import game.components.core.Shape;
import game.components.core.shapes.Circle;
import game.components.core.shapes.Polygon;
import game.components.core.shapes.Rectangle;
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
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by niklasmh on 10.04.16.
 */
public class GameController implements Initializable, CurrentScreen, Global {
    private ScreenController scrCtrl;
    private GraphicsContext ctx;
    private ViewPoint viewPoint;
    @FXML Canvas canvas;
    @FXML VBox gameMenu;
    @FXML VBox gamePlay;
    private boolean showMenu;
    private double zoom = 1;

    @Override
    public void initialize (URL url, ResourceBundle src) {
        ctx = canvas.getGraphicsContext2D();
        showMenu(false);

        viewPoint = new ViewPoint(100, Math.PI);
        Player player = new Player(100, Math.PI);

        new AnimationTimer() {
            @Override
            public void handle (long time) {
                if (scrCtrl.getScreen().equals("game")) {
                    if (showMenu) {
                        if (isKeyPressed("P")) {
                            showMenu(false);
                        }

                        if (isKeyPressed("BACK_SPACE")) {
                            goToMainMenu();
                        }

                        removeKeysPressed();
                    } else {

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

                        if (isKeyDown("Z")) {
                            zoom += .01;
                        }

                        if (isKeyDown("X")) {
                            zoom -= .01;
                        }

                        if (isKeyPressed("P")) {
                            showMenu();
                        }

                        if (isKeyPressed("BACK_SPACE")) {
                            goToMainMenu();
                        }

                        removeKeysPressed();

                        /**
                         * Movements and coordinates.
                         */
                        viewPoint.setPosition(player.getY(), player.getX());

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
                        ctx.setStroke(Color.BLACK);
                        ctx.setLineWidth(1);
                        ctx.fillOval(vpx(0, 0) - vps(100) / 2, vpy(0, 0) - vps(100) / 2, vps(100), vps(100));
                        ctx.strokeOval(vpx(0, 0) - vps(100) / 2, vpy(0, 0) - vps(100) / 2, vps(100), vps(100));

                        /**
                         * Drawing the player.
                         */
                        ctx.setLineWidth(1);
                        drawBody(ctx, player, false);
                        drawBody(ctx, player, true);
                    }
                }
            }
        }.start();
    }

    /**
     * Draw shape from body.
     */
    private void drawBody (Body body, boolean debugDraw) {
        drawBody(this.ctx, body, debugDraw);
    }

    /**
     * Draw shape from body.
     */
    private void drawBody (Body body) {
        drawBody(this.ctx, body, false);
    }

    /**
     * Draw shape from body.
     */
    private void drawBody (GraphicsContext gc, Body body) {
        drawBody(gc, body, false);
    }

    /**
     * Draw shape from body into specified canvas.
     */
    private void drawBody (GraphicsContext gc, Body body, boolean debugDraw) {
        double gx = body.getX();
        double gy = body.getY();
        double x, y, r, w, h;
        double[] xP, yP;

        if (debugDraw) {
            gc.setStroke(Color.BLUE);
        }

        for (Shape shape : body.getShapes()) {
            x = shape.getX();
            y = shape.getY();

            switch (shape.getType()) {
                case "Circle":
                    Circle circ = (Circle) shape;
                    r = circ.getCircleRadius();

                    if (debugDraw) {
                        gc.strokeOval(lvpx(gx, gy, x, y) - vps(r) / 2, lvpy(gx, gy, x, y) - vps(r) / 2, vps(r), vps(r));
                    } else {
                        gc.fillOval(lvpx(gx, gy, x, y) - vps(r) / 2, lvpy(gx, gy, x, y) - vps(r) / 2, vps(r), vps(r));
                    }

                    break;
                case "Rectangle":
                    Rectangle rect = (Rectangle) shape;
                    w = rect.getW();
                    h = rect.getH();

                    xP = new double[] {
                            lvpx(gx, gy, x, y),
                            lvpx(gx, gy, x + w, y),
                            lvpx(gx, gy, x + w, y + h),
                            lvpx(gx, gy, x, y + h)
                    };

                    yP = new double[] {
                            lvpy(gx, gy, x, y),
                            lvpy(gx, gy, x + w, y),
                            lvpy(gx, gy, x + w, y + h),
                            lvpy(gx, gy, x, y + h)
                    };

                    if (debugDraw) {
                        gc.strokePolygon(xP, yP, 4);
                    } else {
                        gc.fillPolygon(xP, yP, 4);
                    }

                    break;
                case "Polygon":
                    Polygon poly = (Polygon) shape;
                    List<LocalPoint> points = poly.getPoints();

                    xP = new double[points.size()];
                    for (int i = 0; i < xP.length; i++) {
                        xP[i] = lvpx(gx, gy, points.get(i).getX(), points.get(i).getY());
                    }

                    yP = new double[points.size()];
                    for (int i = 0; i < yP.length; i++) {
                        yP[i] = lvpy(gx, gy, points.get(i).getX(), points.get(i).getY());
                    }

                    if (debugDraw) {
                        gc.strokePolygon(xP, yP, xP.length);
                    } else {
                        gc.fillPolygon(xP, yP, xP.length);
                    }

                    break;
            }
        }

        if (debug) {
            gc.setStroke(Color.BLUE);
            gc.setLineWidth(1);
            gc.strokeOval(vpx(gx, gy) - .5, vpy(gx, gy) - .5, 1, 1);
        }
    }

    /**
     * Returns the local x coordinate based on the global and local shapes.
     *
     * @param gx = Global X
     * @param gy = Global Y
     * @param x = Local X
     * @param y = Local Y
     * @return = x in double
     */
    private double lvpx (double gx, double gy, double x, double y) {
        double lrad = Math.atan2(y, x);
        return vpx(gx, gy) + vps(Math.sqrt(x * x + y * y) * Math.sin(gx + lrad));
    }

    /**
     * Returns the local y coordinate based on the global and local shapes.
     *
     * @param gx = Global X
     * @param gy = Global Y
     * @param x = Local X
     * @param y = Local Y
     * @return = y in double
     */
    private double lvpy (double gx, double gy, double x, double y) {
        double lrad = Math.atan2(y, x);
        return vpy(gx, gy) + vps(Math.sqrt(x * x + y * y) * Math.cos(gx + lrad));
    }

    /**
     * Convert scaling to right scale relative to screen.
     * VPX = View Point Scale
     */
    private double vps(double val) {
        return val * this.zoom;
    }

    /**
     * Convert all points relative to ViewPoint.
     * VPX = View Point X
     */
    private double vpx (double x, double y) {
        double posX = vps(y * Math.sin(x) - this.viewPoint.getY() * Math.sin(this.viewPoint.getX()));
        return posX + canvas.getWidth() / 2;
    }

    /**
     * Convert all points relative to ViewPoint.
     * VPX = View Point X
     */
    private double vpy (double x, double y) {
        double posY = vps(y * Math.cos(x) - this.viewPoint.getY() * Math.cos(this.viewPoint.getX()));
        return posY + canvas.getHeight() / 2;
    }

    /**
     * Checks different key combinations. Just to make the
     * game more modular and possible to play with
     * different key combinations.
     *
     * @param key = string describing the key action
     * @return = true if key combination is true
     */
    private boolean isKeyDown (String key) {
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
                return keyCodes.contains(key);
        }
    }

    /**
     * Checks if key is pressed.
     *
     * @param key = String describing key
     * @return = true if pressed (this means one time, not longer)
     */
    private boolean isKeyPressed (String key) {
        boolean pressed = keyPressedCodes.contains(key);
        keyPressedCodes.removeAll(Collections.singleton(key));
        return pressed;
    }

    /**
     * Remove all keys pressed.
     */
    private void removeKeysPressed () {
        keyPressedCodes.clear();
    }

    /**
     * Removes key pressed from another list when noticed.
     * Nice to use when key only should be used one time in the loop.
     *
     * @param keyCode = string, like "LEFT", "A" etc...
     */
    private boolean removeKeyPressed (String keyCode) {
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
        goToMainMenu();
    }

    private void goToMainMenu () {
        this.resetGame();
        this.scrCtrl.setScreen("menu");
    }

    @FXML
    public void toggleMenu (ActionEvent evt) {
        toggleMenu();
    }

    private void resetGame () {
        showMenu(false);
        ctx.clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
    }

    private void toggleMenu () {
        showMenu(!this.showMenu);
    }

    private void showMenu () {
        showMenu(true);
    }

    /**
     * Shows or hides menu in the game. This will also
     * control the timer in the game.
     *
     * @param show = boolean for showing or hiding
     */
    private void showMenu (boolean show) {
        this.showMenu = show;

        gameMenu.setVisible(show);
        gameMenu.managedProperty().bind(gameMenu.visibleProperty());

        gamePlay.setVisible(!show);
        gamePlay.managedProperty().bind(gamePlay.visibleProperty());
    }
}
