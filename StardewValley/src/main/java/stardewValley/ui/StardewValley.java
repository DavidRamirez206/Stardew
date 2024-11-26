package stardewValley.ui;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import stardewValley.control.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.io.IOException;

public class StardewValley extends Application {

    public static int MAIN_SCENE = 1;
    private static StardewValley instance;
    private Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        //stage.setFullScreen(true);
        instance = this;
        setup(stage);
        loadScene(stage, MAIN_SCENE);
        stage.show();

    }

    private void loadScene(Stage stage, int sceneId) throws IOException {
        FXMLLoader fxmlLoader;
        Scene scene;

        if (sceneId == 1) {
            fxmlLoader = fxmlLoader("mainScene");
            StackPane root = (StackPane) fxmlLoader.load();
            scene = new Scene(root);
        } else if(sceneId == 2) {
            fxmlLoader = fxmlLoader("instructions");
            StackPane root = (StackPane) fxmlLoader.load();
            scene = new Scene(root);
        }else {
            fxmlLoader = fxmlLoader("controller-view");
            scene = new Scene(fxmlLoader.load(), 320, 240);

            stage.setOnCloseRequest(event -> {
                Controller controller = fxmlLoader.getController();
                controller.setRunning();
            });
        }
        stage.setScene(scene);
        stage.setFullScreen(true);
    }

    public static StardewValley getInstance() {
        return instance;
    }

    public void changeScene(int sceneId) {
        MAIN_SCENE = sceneId;
        try {
            loadScene(stage, MAIN_SCENE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private FXMLLoader fxmlLoader(String fxml){
        return new FXMLLoader(getClass().getResource("/fxml/" + fxml + ".fxml"));
    }


    private void setup(Stage stage){
        stage.setFullScreen(true);
        stage.setTitle("Stardew Valley");

        Image icon = new Image(getClass().getResourceAsStream("/img/icon/icon.png"));

        stage.getIcons().add(icon);

        stage.setFullScreenExitHint("");

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX(screenBounds.getMinX());
        stage.setY(screenBounds.getMinY());
        stage.setWidth(screenBounds.getWidth());
        stage.setHeight(screenBounds.getHeight());
    }

    public static void main(String[] args) {
        launch(args);
    }
}