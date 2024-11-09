package stardewValley.screens;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import stardewValley.control.Controller;
import stardewValley.model.Foxy;
import stardewValley.model.House;

public class ScreenA extends SceneBase {

    private House house;

    public ScreenA(Canvas canvas, String img, Foxy foxy) {
        super(canvas, img, foxy);
        house = new House(canvas);
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
        house.draw1();
        super.foxyRedraw();
        house.draw2();
    }

    @Override
    public void updateObjects(){
        //this.foxy.updatePosition();

        this.house.setPaint(false);
        this.house.setPaint2(false);
    }

    @Override
    public void onKeyPressed(KeyEvent event){

        if (event.getCode() == KeyCode.SPACE){
            super.foxy.setScene(2);
            //super.foxy.getPosition().setX(940);
            //super.foxy.getPosition().setY(680);

            /*
            super.canvas.heightProperty().addListener((obs, oldVal, newVal) -> {

            });

             */

            Controller.SCREEN = 1;

        } else {
            super.foxy.onKeyPressed(event);
        }
    }

    @Override
    public void onKeyReleased(KeyEvent event){
        foxy.onKeyReleased(event);
    }
}
