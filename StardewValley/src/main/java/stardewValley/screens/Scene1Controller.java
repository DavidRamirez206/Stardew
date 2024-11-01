package stardewValley.screens;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import stardewValley.control.SceneManager;
import stardewValley.model.Foxy;

public class Scene1Controller extends SceneBase{

    @FXML
    private Canvas canvas;

    private GraphicsContext gc;

    private Foxy foxy;

    private SceneManager sceneManager;

    private Image background;

    private volatile boolean running = true;

    public SceneManager getSceneManager() {
        return sceneManager;
    }

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Foxy getFoxy() {
        return foxy;
    }

    public void setFoxy(Foxy foxy) {
        this.foxy = foxy;
    }

    public void initialize() {

        background = new Image(getClass().getResourceAsStream("/img/background/scene1Background.png"));

        canvas.widthProperty().bind(((Pane)canvas.getParent()).widthProperty());
        canvas.heightProperty().bind(((Pane)canvas.getParent()).heightProperty());

        gc = canvas.getGraphicsContext2D();

        gc.drawImage(background, 0, 0, canvas.getWidth(), canvas.getHeight());

        this.foxy = new Foxy(this.canvas);



        canvas.widthProperty().addListener((obs, oldVal, newVal) -> redraw());
        canvas.heightProperty().addListener((obs, oldVal, newVal) -> redraw());

        canvas.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.G){
                sceneManager.switchToScene2();
            }
            foxy.onKeyPressed(event);
        });

        canvas.setOnKeyReleased(event -> {
            foxy.onKeyReleased(event);
        });

        startAnimationThread();

        /*
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(80), e -> redraw(gc, image)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

         */


        /*
        new Thread(
            () -> {
                while (true){
                    Platform.runLater( () -> {
                        redraw();
                    });

                    try {
                        Thread.sleep(80);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        ).start();

         */


    }

    private void startAnimationThread() {
        /*
        new Thread(
            () -> {
                while (true) {
                    Platform.runLater(() -> redraw());
                    try {
                        Thread.sleep(80);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }).start();

         */

        Thread animationThread = new Thread(() -> {
            while (running) {
                Platform.runLater(() -> redraw());
                try {
                    Thread.sleep(80);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        animationThread.setDaemon(true);
        animationThread.start();
    }

    public void stopAnimation() {
        running = false;
    }

    private void redraw() {
        this.gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        this.gc.drawImage(background, 0, 0, canvas.getWidth(), canvas.getHeight());
        foxy.draw();
    }
}
