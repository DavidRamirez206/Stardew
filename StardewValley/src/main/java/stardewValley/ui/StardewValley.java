package stardewValley.ui;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import stardewValley.control.Controller;
import stardewValley.control.SceneManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.io.IOException;
import javafx.stage.Stage;
public class StardewValley extends Application {

    /*
    @Override
    public void start(Stage stage) throws Exception {
        new SceneManager(stage);
    }

     */

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StardewValley.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        stage.setTitle("Stardew Valley");

        Image icon = new Image(getClass().getResourceAsStream("/img/icon/icon.png"));

        stage.getIcons().add(icon);

        stage.setFullScreenExitHint("");

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX(screenBounds.getMinX());
        stage.setY(screenBounds.getMinY());
        stage.setWidth(screenBounds.getWidth());
        stage.setHeight(screenBounds.getHeight());
        stage.setScene(scene);

        stage.setOnCloseRequest(event -> {
            Controller controller = fxmlLoader.getController();
            controller.setRunning();
        });
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}