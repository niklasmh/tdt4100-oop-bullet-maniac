package game;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Created by niklasmh on 12.04.16.
 */
public class WindowBorder extends AnchorPane {
    private double offX = 0;
    private double offY = 0;
    private Stage stg;

    public WindowBorder(Stage stg, Node node) {
        super();

        this.stg = stg;

        Button btnMin = buildBtn("_", evt -> {
            stg.setMaximized(stg.isMaximized());
        });

        AnchorPane.setRightAnchor(btnMin, 64.0);
        AnchorPane.setTopAnchor(btnMin, 8.0);

        Button btnMax = buildBtn("[ ]", evt -> {
            stg.setMaximized(!stg.isMaximized());
        });

        AnchorPane.setRightAnchor(btnMax, 64.0);
        AnchorPane.setTopAnchor(btnMax, 8.0);

        Button btnClose = buildBtn("X", evt -> {
            Platform.exit();
        });

        AnchorPane.setRightAnchor(btnClose, 8.0);
        AnchorPane.setTopAnchor(btnClose, 8.0);

        AnchorPane.setTopAnchor(node, 0.0);
        AnchorPane.setRightAnchor(node, 0.0);
        AnchorPane.setBottomAnchor(node, 0.0);
        AnchorPane.setLeftAnchor(node, 0.0);

        this.getChildren().addAll(node, btnMax, btnClose);

        this.setOnMousePressed((MouseEvent event) -> {
            offX = event.getSceneX();
            offY = event.getSceneY();
        });

        this.setOnMouseDragged((MouseEvent event) -> {
            this.stg.setX(event.getScreenX() - offX);
            this.stg.setY(event.getScreenY() - offY);
        });
    }

    private Button buildBtn(String btnTxt, EventHandler<ActionEvent> onAction) {
        Button btn = new Button(btnTxt);
        btn.setMinSize(42, 42);
        btn.setMaxSize(42, 42);
        btn.setOnAction(onAction);
        return btn;
    }
}
