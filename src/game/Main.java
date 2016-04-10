package game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stg) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        stg.setTitle("Bullet Maniac");
        stg.setScene(new Scene(root, 300, 275));
        stg.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
