package stardewValley.screens;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import stardewValley.control.Controller;
import stardewValley.model.Foxy;
import stardewValley.model.House;
import stardewValley.model.Tree;

public class ScreenA extends SceneBase {

    private House house;
    private Tree tree;

    public ScreenA(Canvas canvas, String img, Foxy foxy) {
        super(canvas, img, foxy);
        house = new House(canvas, "/img/object/halfHouse1", "/img/object/halfHouse2", 0.5, 0.3993055556, 0.1302083332, 0.1157407408);
        tree = new Tree(canvas, "/img/object/tree/1/Tree1", "/img/object/tree/1/Tree2",  0.37109375, 0.4513888887, 0.1302083332, 0.1157407408);
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
        tree.draw2();
        house.draw2();
        super.foxyRedraw();
        house.draw1();
        tree.draw1();
    }

    @Override
    public void updateObjects(){
        //this.foxy.updatePosition();

        this.house.setPaint(false);
        this.house.setPaint(false);
        this.tree.setPaint(false);
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
