package stardewValley.control;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import stardewValley.model.Foxy;
import stardewValley.screens.Scene1Controller;
import stardewValley.screens.Scene2Controller;


import java.io.IOException;

public class SceneManager {

    private Stage stage;
    private Canvas canvas;
    private Foxy foxy;

    private StackPane root;
    private BorderPane borderPane;
    private AnchorPane anchorPane;


    public SceneManager(Stage stage) {
        this.stage = stage;

        setup(this.stage);

        //loadMainScene();
        switchToScene2();
    }

    public Foxy getFoxy() {
        return foxy;
    }

    public void loadMainScene(){
        try {
            FXMLLoader fxmlLoader = fxmlLoader("mainScene");
            root = (StackPane) fxmlLoader.load();
            AppController appController = fxmlLoader.getController();
            appController.setSceneManager(this);

            Scene scene = new Scene(root);

            stage.setScene(scene);
            //stage.setFullScreen(true);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadInstructions(){
        try {
            //System.out.println("Loading Instructions");
            FXMLLoader fxmlLoader = fxmlLoader("instructions");
            root = (StackPane) fxmlLoader.load();
            InstructionsController instructions = (InstructionsController) fxmlLoader.getController();
            instructions.setSceneManager(this);
            Scene scene = new Scene(root);

            stage.setScene(scene);
            //stage.setFullScreen(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void switchToScene1(){
        try {
            //System.out.println("Loading Instructions");
            FXMLLoader fxmlLoader = fxmlLoader("scene1");

            anchorPane = (AnchorPane) fxmlLoader.load();
            Scene1Controller scene1 = (Scene1Controller) fxmlLoader.getController();
            scene1.setSceneManager(this);
            Scene scene = new Scene(anchorPane);

            this.canvas = scene1.getCanvas();
            this.foxy = scene1.getFoxy();

            stage.setScene(scene);
            //stage.setFullScreen(true);

            stage.setOnCloseRequest(event -> {

                scene1.stopAnimation();
            });
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    public void switchToScene2(){
        try {
            //System.out.println("Loading Instructions");
            FXMLLoader fxmlLoader = fxmlLoader("scene2");

            anchorPane = (AnchorPane) fxmlLoader.load();
            Scene2Controller scene2 = (Scene2Controller) fxmlLoader.getController();
            scene2.setSceneManager(this);
            Scene scene = new Scene(anchorPane);
            //scene2.setCanvas(this.canvas);
            //scene2.setFoxy(foxy);

            stage.setScene(scene);
            //stage.setFullScreen(true);

            stage.setOnCloseRequest(event -> {

                scene2.stopAnimation();
            });
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void switchToScene3(){

    }

    public void endGame(){

    }

    private FXMLLoader fxmlLoader(String fxml){
        return new FXMLLoader(getClass().getResource("/fxml/" + fxml + ".fxml"));
    }


    private void setup(Stage stage){

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


}
