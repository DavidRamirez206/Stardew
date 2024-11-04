package stardewValley.screens;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import stardewValley.model.Foxy;
import stardewValley.model.*;

public abstract class SceneBase {
    protected Canvas canvas;
    protected GraphicsContext gc;
    protected boolean running;
    protected Foxy foxy;
    protected Image background;

    public SceneBase(Canvas canvas, String img, Foxy foxy) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        this.running = true;
        this.foxy = foxy;
        this.loadIMG(img);
    }

    public void updateCanvas(){
        canvas.widthProperty().bind(((Pane)canvas.getParent()).widthProperty());
        canvas.heightProperty().bind(((Pane)canvas.getParent()).heightProperty());

        gc = canvas.getGraphicsContext2D();

        gc.drawImage(background, 0, 0, canvas.getWidth(), canvas.getHeight());

        canvas.widthProperty().addListener((obs, oldVal, newVal) -> {
            foxy.setChangePosition(true);
            update();
            redraw();
        });

        canvas.heightProperty().addListener((obs, oldVal, newVal) -> {
            foxy.setChangePosition(true);
            update();
            redraw();
        });
    }

    private void update(){
        this.foxy.setPaint(false);
        //this.foxy.updatePosition();
        PurplePortal.paint = false;
        House.paint = false;
    }

    private void loadIMG(String img) {
        this.background = new Image(getClass().getResourceAsStream("/img/background/" + img + ".png"));
    }

    public void gcUpdate(){
        this.gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
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