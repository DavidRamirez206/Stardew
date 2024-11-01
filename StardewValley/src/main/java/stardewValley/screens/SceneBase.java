package stardewValley.screens;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

public abstract class SceneBase {
    protected Canvas canvas;
    protected GraphicsContext gc;

    public SceneBase(Canvas canvas) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
    }

    public abstract void paint();

    public abstract void onKeyPressed(KeyEvent event);

    public abstract void onKeyReleased(KeyEvent event);
}