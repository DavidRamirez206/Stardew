package stardewValley.screens;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import stardewValley.control.Controller;
import stardewValley.model.*;

public class ScreenB extends SceneBase {
    private PurplePortal portal;

    public ScreenB(Canvas canvas, String img, Foxy foxy) {
        super(canvas, img, foxy);
        this.portal = new PurplePortal(canvas);
        draw();
    }

    @Override
    public void draw() {
        super.updateCanvas();
    }

    @Override
    public void redraw(){
        super.gcUpdate();
        //super.foxy.updatePosition();
        portal.draw();
        super.foxyRedraw();
    }

    @Override
    public void updateObjects(){
        //this.foxy.updatePosition();
        portal.setPaint(false);
    }

    @Override
    public void onKeyPressed(KeyEvent event){
        super.foxy.onKeyPressed(event);

        if (event.getCode() == KeyCode.SPACE){
            super.foxy.setScene(3);
            Controller.SCREEN = 2;
        }
    }

    @Override
    public void onKeyReleased(KeyEvent event){
        super.foxy.onKeyReleased(event);
    }


}
