package stardewValley.screens;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import stardewValley.control.Controller;
import stardewValley.model.Foxy;

public class ScreenC extends SceneBase{

    public ScreenC(Canvas canvas, String img, Foxy foxy) {
        super(canvas, img, foxy);
        draw();
    }

    @Override
    public void draw(){
        super.updateCanvas();
    }

    public void redraw(){
        super.foxyRedraw();
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


}
