package stardewValley.screens;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import stardewValley.control.Controller;
import stardewValley.model.*;

public class ScreenB extends SceneBase {
    private VBar vBar;
    private HBar hBar;
    private PurplePortal portal;

    public ScreenB(Canvas canvas, String img, Foxy foxy) {
        super(canvas, img, foxy);
        this.portal = new PurplePortal(canvas);
        initBar(canvas);
        draw();
    }

    @Override
    public void draw() {
        super.updateCanvas();
        drawBar();
    }

    @Override
    public void redraw(){
        super.gcUpdate();
        //super.foxy.updatePosition();
        portal.draw();
        super.foxyRedraw();
        drawBar();
    }

    @Override
    public void onKeyPressed(KeyEvent event){
        super.foxy.onKeyPressed(event);

        if (event.getCode() == KeyCode.SPACE){
            super.foxy.setScene(1);
            Controller.SCREEN = 0;
        }
    }

    @Override
    public void onKeyReleased(KeyEvent event){
        super.foxy.onKeyReleased(event);
    }

    private void initBar(Canvas canvas){
        this.vBar = new VBar(canvas);
        this.hBar = new HBar(canvas);
    }

    private void drawBar(){
        this.vBar.draw();
        this.hBar.draw();
    }


}
