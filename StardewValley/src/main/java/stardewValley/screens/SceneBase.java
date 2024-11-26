package stardewValley.screens;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import stardewValley.control.Controller;
import stardewValley.model.Foxy;
import stardewValley.model.River;

public abstract class SceneBase {
    protected Canvas canvas;
    protected GraphicsContext gc;
    protected boolean running;
    protected Foxy foxy;
    protected Image background;

    protected River river;
    protected boolean firstTimeX, firstTimeY;

    public SceneBase(Canvas canvas, String img, Foxy foxy) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        river = new River(canvas);
        this.running = true;
        firstTimeX = true;
        firstTimeY = true;
        this.foxy = foxy;
        this.loadIMG(img);
    }

    public void updateCanvas(){
        canvas.widthProperty().bind(((Pane)canvas.getParent()).widthProperty());
        canvas.heightProperty().bind(((Pane)canvas.getParent()).heightProperty());

        gc = canvas.getGraphicsContext2D();

        gc.drawImage(background, 0, 0, canvas.getWidth(), canvas.getHeight());

        canvas.widthProperty().addListener((obs, oldVal, newVal) -> {
            firstPositionMovingObjectsX();

            updateFoxy();
            river.setPaint(false);
            updateObjects();
            redraw();
        });

        canvas.heightProperty().addListener((obs, oldVal, newVal) -> {
            firstPositionMovingObjectsY();
            updateFoxy();
            river.setPaint(false);
            updateObjects();
            redraw();
        });

        canvas.setOnMouseClicked(event -> {
            double x = event.getX(); // X
            double y = event.getY(); // Y

            System.out.println("Coordenadas del clic -> X: " + x + ", Y: " + y);
        });
    }

    protected abstract void firstPositionMovingObjectsX();
    protected abstract void firstPositionMovingObjectsY();

    private void updateFoxy(){
        foxy.setChangePosition(true);
        this.foxy.setPaint(false);
    }

    public abstract void updateObjects();

    private void loadIMG(String img) {
        this.background = new Image(getClass().getResourceAsStream("/img/background/" + img + ".png"));
    }

    public void gcUpdate(){
        this.gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        //Esto es para que primero se dibuje el río por detrás del fondo del scenario 3
        if(Controller.SCREEN == 2){
            river.draw();
        }
        this.gc.drawImage(background, 0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void foxyRedraw() {
        foxy.draw();
    }

    public abstract void redraw();

    public abstract void draw();

    public abstract void onKeyPressed(KeyEvent event);

    public abstract void onKeyReleased(KeyEvent event);
}