package stardewValley.control;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import stardewValley.model.Foxy;
import stardewValley.screens.*;
import stardewValley.screens.ScreenB;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private ArrayList<SceneBase> screens;
    private boolean isRunning;
    public static int SCREEN = 2;
    private Foxy foxy;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        runningScenes();
    }

    public void runningScenes(){
        this.graphicsContext = canvas.getGraphicsContext2D();
        this.foxy = new Foxy(this.canvas);
        this.foxy.setScene(3);
        screens = new ArrayList<>(3);
        screens.add(new ScreenA(canvas, "scene1Background", foxy));
        screens.add(new ScreenB(canvas, "scene2Background", foxy));
        screens.add(new ScreenC(canvas, "scene3Background", foxy));

        initActions();

        isRunning = true;
        // Hilo
        new Thread(() -> { // Runable -> lambda
            while (isRunning){
                Platform.runLater( () -> {
                    screens.get(SCREEN).redraw();
                });
                try {
                    Thread.sleep(80);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void setRunning(){
        isRunning = false;
    }

    public void initActions(){
        canvas.setOnKeyPressed(event -> {
            screens.get(SCREEN).onKeyPressed(event);
        });

        canvas.setOnKeyReleased(event -> {
            screens.get(SCREEN).onKeyReleased(event);
        });
    }
}
