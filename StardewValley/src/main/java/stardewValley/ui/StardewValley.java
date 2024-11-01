package stardewValley.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import stardewValley.control.SceneManager;
public class StardewValley extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        new SceneManager(stage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}