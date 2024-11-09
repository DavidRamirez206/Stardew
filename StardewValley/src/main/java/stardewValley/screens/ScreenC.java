package stardewValley.screens;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import stardewValley.control.Controller;
import stardewValley.model.Foxy;
import stardewValley.model.PurplePortal;
import stardewValley.model.Rock;

public class ScreenC extends SceneBase{

    private PurplePortal portal;
    private Rock rockE;
    private Rock rockD;
    private Rock rockC;
    private Rock rockB;
    private Rock rockA;

    public ScreenC(Canvas canvas, String img, Foxy foxy) {
        super(canvas, img, foxy);
        load(canvas);
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
        drawRock();
        super.foxyRedraw();
    }

    @Override
    public void updateObjects(){
        this.portal.setPaint(false);
        reloadRocks(false);
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

    private void load(Canvas canvas){
        portal = new PurplePortal(canvas);
        rocks(canvas);
    }

    private void rocks(Canvas canvas){
        rockA = new Rock(canvas, 0.5013020834, 0.1967592592, 0);
        rockB = new Rock(canvas, 0.2799479167, 0.09259259256, 0);
        rockC = new Rock(canvas, 0.8203125, 0.3703703702, 0);
        rockD = new Rock(canvas, 0.3190104167, 0.4745370369, 0);
        rockE = new Rock(canvas, 0.05208333333, 0.1504629629, 5);
    }

    private void reloadRocks(boolean reload){
        rockA.setPaint(reload);
        rockB.setPaint(reload);
        rockC.setPaint(reload);
        rockD.setPaint(reload);
        rockE.setPaint(reload);
    }

    private void drawRock(){
        rockA.draw();
        rockB.draw();
        rockC.draw();
        rockD.draw();
        rockE.draw();
    }


}
