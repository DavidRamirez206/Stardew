package stardewValley.screens;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import stardewValley.control.Controller;
import stardewValley.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ScreenB extends SceneBase {
    private PurplePortal portal;
    private List<Bear> bears;
    private int nBears;

    public ScreenB(Canvas canvas, String img, Foxy foxy) {
        super(canvas, img, foxy);
        this.portal = new PurplePortal(canvas, 0, 0.4016203704, 3, 6.5);
        nBears = new Random().nextInt(5) + 1;
        //nBears = 1;
        loadBears();
        draw();
    }

    @Override
    public void draw() {
        super.updateCanvas();
    }

    private void loadBears(){
        this.bears = new ArrayList<>();
        for (int i = 0; i < nBears; i++) {
            bears.add(new Bear(canvas));
        }
    }

    @Override
    public void redraw(){
        super.gcUpdate();
        //super.foxy.updatePosition();
        portal.draw();
        super.foxyRedraw();
        drawBears();
        checkForCutting();
    }

    @Override
    public void updateObjects(){
        //this.foxy.updatePosition();
        updateBears();
        portal.setPaint(false);
    }

    @Override
    public void onKeyPressed(KeyEvent event){
        super.foxy.onKeyPressed(event);

        if (event.getCode() == KeyCode.SPACE){
            super.foxy.setEsc(3);
            Controller.SCREEN = 2;
        }
    }

    @Override
    protected void firstPositionMovingObjectsX() {
        if(firstTimeX){
            bearsPositionX();
            foxy.setFirstPosition(true);
            firstTimeX = false;
        }
    }

    @Override
    protected void firstPositionMovingObjectsY() {
        if(firstTimeY){
            bearsPositionY();
            foxy.setFirstPosition(true);
            firstTimeY = false;
        }
    }

    @Override
    public void onKeyReleased(KeyEvent event){
        super.foxy.onKeyReleased(event);
    }

    private void updateBears(){
        for (Bear bear : bears) {
            bear.setChangePosition(true);
            bear.setPaint(false);
        }
    }

    private void bearsPositionX(){
        for (Bear bear : bears) {
            bear.setFirstPosition(true);
        }
    }

    private void bearsPositionY(){
        for (Bear bear : bears) {
            bear.setFirstPosition(true);
        }
    }

    private void drawBears(){
        for (Bear bear : bears) {
            if(!bear.isDead()){
                bear.update();
                bear.draw();
            }
        }
    }

    public void checkForCutting() {
        for (Bear bear : bears) {
            if(!bear.isDead()){
                String tool = foxy.getStringTool();
                if(bear.handleCutting(foxy.getPosition(), foxy.getWidth(), foxy.getHeight(), tool, foxy.isCutting() && (foxy.isAxe() || foxy.isPick()))){
                    if(foxy.isPick()){
                        foxy.getPickaxe().setDurability();
                    } else {
                        foxy.getAxe().setDurability();
                    }
                    bear.setKicked(1);
                    if (bear.getKicked() == 10){
                        bear.setIsDead(true);
                        foxy.setScore(200);
                    }
                }
            }

        }
    }
}
