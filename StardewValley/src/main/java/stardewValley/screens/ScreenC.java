package stardewValley.screens;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import stardewValley.control.Controller;
import stardewValley.model.Foxy;
import stardewValley.model.PurplePortal;
import stardewValley.model.River;

public class ScreenC extends SceneBase{

    private River river;
    private PurplePortal portal;

    public ScreenC(Canvas canvas, String img, Foxy foxy) {
        super(canvas, img, foxy);
        river = new River(canvas);
        portal = new PurplePortal(canvas);
        portal.setYPosition(0.5902777776);
        portal.setNewSizeH(1.5);
        portal.setNewSizeW(1);
        draw();
    }

    @Override
    public void draw(){
        super.updateCanvas();
    }

    public void redraw(){
        super.gcUpdate();
        portal.draw();
        super.foxyRedraw();
    }

    @Override
    public void updateObjects(){
        this.portal.setPaint(false);
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
