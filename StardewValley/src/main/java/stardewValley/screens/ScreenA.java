package stardewValley.screens;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import stardewValley.control.Controller;
import stardewValley.model.Foxy;
import stardewValley.model.House;
import stardewValley.model.Position;
import stardewValley.model.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScreenA extends SceneBase {

    private House house;
    private List<Tree> trees;
    private final int N_TREES = 10;
    private Position[] positions = {
            new Position(0.1041666667, 0.04629629628),
            new Position(0.2473958333, 0.04629629628),
            new Position(0.7161458334, 0.04629629628),
            new Position(0.8528645834, 0.04629629628),

            new Position(0.3515625, 0.3472222221),
            new Position(0.625, 0.3472222221),


            new Position(0.078125, 0.6249999998),
            new Position(0.2083333333, 0.6249999998),
            new Position(0.7096354167, 0.6249999998),
            new Position(0.8528645834, 0.6249999998),
    };

    public ScreenA(Canvas canvas, String img, Foxy foxy) {
        super(canvas, img, foxy);
        house = new House(canvas, "/img/object/halfHouse1", "/img/object/halfHouse2", 0.5, 0.3993055556, 0.1302083332, 0.1157407408);
        loadTrees();
        draw();
    }

    private void loadTrees(){
        this.trees = new ArrayList<>();
        for (int i = 0; i < N_TREES; i++) {
            Position position = positions[i];
            trees.add(new Tree(canvas, position.getX(), position.getY(), 0, 0.1302083332, 0.1157407408));
        }
    }

    @Override
    public void draw() {
        super.updateCanvas();
    }

    @Override
    public void redraw(){
        super.gcUpdate();
        //super.foxy.updatePosition();
        trees.get(0).otherDraw2();
        house.draw2();
        drawTree2();
        super.foxyRedraw();
        house.draw1();
        drawTree1();
        checkForCutting();
        //trees.get(0).otherDraw();
    }

    @Override
    public void updateObjects(){
        //this.foxy.updatePosition();

        this.house.setPaint(false);
        updateTrees();
    }

     @Override
    public void onKeyPressed(KeyEvent event){

        if (event.getCode() == KeyCode.SPACE){
            super.foxy.setScene(2);

            Controller.SCREEN = 1;

        } else {
            super.foxy.onKeyPressed(event);
        }
    }

    @Override
    public void onKeyReleased(KeyEvent event){
        foxy.onKeyReleased(event);
    }

    private void updateTrees(){
        for (Tree tree : trees) {
            tree.setPaint(false);
        }
    }

    private void drawTree1(){
        for (Tree tree : trees) {
            if(!tree.getCut()){
                tree.otherDraw();
            }
        }
    }

    private void drawTree2(){
        for (Tree tree : trees) {
            if(!tree.getCut()){
                tree.otherDraw2();
            }
        }
    }

    public void checkForCutting() {
        for (Tree tree : trees) {
            if (tree.isFoxyInside(foxy.getPosition(), foxy.getFoxySizeW(), foxy.getFoxySizeH())){
                if(!tree.getCut()){
                    System.out.println("Foxy Inside Tree");
                }
            }
        }
    }
}
