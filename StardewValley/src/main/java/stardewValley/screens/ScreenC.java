package stardewValley.screens;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import stardewValley.control.Controller;
import stardewValley.model.Foxy;
import stardewValley.model.River;

public class ScreenC extends SceneBase{

    private River river;

    public ScreenC(Canvas canvas, String img, Foxy foxy) {
        super(canvas, img, foxy);
        river = new River(canvas);
        draw();
    }

    @Override
    public void draw(){
        super.updateCanvas();
    }

    public void redraw(){
        super.gcUpdate();
        super.foxyRedraw();
    }

    @Override
    public void updateObjects(){
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
