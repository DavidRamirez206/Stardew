package stardewValley.screens;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import stardewValley.control.Controller;
import stardewValley.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ScreenA extends SceneBase {

    private House house;

    private int cropOne_1;
    private boolean cropOne;

    private int cropTwo_1;
    private boolean cropTwo;

    private int cropThree_1;
    private boolean cropThree;

    private int cropFour_1;
    private boolean cropFour;

    private List<Tree> trees;
    private final int N_TREES = 10;
    private Position[] positions = {
            new Position(0.1041666667, 0.04629629628), //1
            new Position(0.2473958333, 0.04629629628), // 2
            new Position(0.75625, 0.04629629628), // 3
            new Position(0.9223958333, 0.04629629628), // 4

            new Position(0.3515625, 0.3472222221), // 5
            new Position(0.625, 0.3472222221), // 6


            new Position(0.1041666667, 0.7175925926), // 7
            new Position(0.2473958333, 0.7175925926), // 8
            new Position(0.75625, 0.7175925926), // 9
            new Position(0.9223958333, 0.7175925926), //10
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
        trees.get(0).otherDraw2();
        house.draw2();
        drawTree2();
        super.foxyRedraw();
        house.draw1();
        drawTree1();
        checkForCutting();

        gc.setStroke(Color.RED);

        gc.strokeRect(
                0,
                0,
                canvas.getWidth() / 3,
                canvas.getHeight() / 3
        );

        gc.strokeRect(
                ( 2 * canvas.getWidth()) / 3,
                0,
                canvas.getWidth() * (2.0 / 3),
                canvas.getHeight() / 3
        );

        gc.strokeRect(
                0,
                (2 * canvas.getHeight()) / 3,
                canvas.getWidth() / 3,
                canvas.getHeight() / 3
        );

        gc.strokeRect(
                ( 2 * canvas.getWidth()) / 3,
                (2 * canvas.getHeight()) / 3,
                canvas.getWidth() * (2.0 / 3),
                canvas.getHeight() / 3
        );
    }

    @Override
    public void updateObjects(){
        //this.foxy.updatePosition();

        this.house.setPaint(false);
        updateTrees();
    }

     @Override
    public void onKeyPressed(KeyEvent event){

        if(event.getCode() == KeyCode.S){

        }

        if (event.getCode() == KeyCode.SPACE){
            super.foxy.setEsc(2);

            Controller.SCREEN = 1;

        } else {
            super.foxy.onKeyPressed(event);
        }
    }

    @Override
    public void onKeyReleased(KeyEvent event){
        foxy.onKeyReleased(event);
    }

    @Override
    protected void firstPositionMovingObjectsX() {
        if(firstTimeX){
            foxy.setFirstPosition(true);
            firstTimeX = false;
        }
    }

    @Override
    protected void firstPositionMovingObjectsY() {
        if(firstTimeY){
            foxy.setFirstPosition(true);
            firstTimeY = false;
        }
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
        String tool = foxy.getStringTool();

        for (Tree tree : trees) {
            if(!tree.getCut() &&
                    tree.handleCutting(foxy.getPosition(), foxy.getWidth(), foxy.getHeight(), tool, foxy.isCutting() && foxy.isAxe())){

                foxy.getAxe().setDurability();
                tree.setKicked(1);

                if(tree.getKicked() == 5){
                    if(foxyInsideRecOne()){
                        cropOne_1++;
                        cropOne = true;
                    } else if(foxyInsideRecTwo()){
                        cropTwo_1++;
                        cropTwo = true;
                    } else if(foxyInsideRecThree()){
                        cropThree_1++;
                        cropThree = true;
                    } else if(foxyInsideRecFour()){
                        cropFour_1++;
                        cropFour = true;
                    }

                    foxy.setWood(40);
                    tree.setCut(true);
                }
            }

        }

        if(cropOne && cropOne_1 == 2){
            System.out.println("Rectangulo 1");
            cropOne = false;
        }

        if(cropTwo && cropTwo_1 == 2){
            System.out.println("Rectangulo 2");
            cropTwo = false;
        }

        if(cropThree && cropThree_1 == 2){
            System.out.println("Rectangulo 3");
            cropThree = false;
        }

        if(cropFour && cropFour_1 == 2){
            System.out.println("Rectangulo 4");
            cropFour = false;
        }
    }

    private boolean foxyInsideRecOne(){
        double x = foxy.getPosition().getX();
        double y = foxy.getPosition().getY();

        return x < (canvas.getWidth() / 3) && y < (canvas.getHeight() / 3);
    }

    private boolean foxyInsideRecTwo(){
        double x = foxy.getPosition().getX();
        double y = foxy.getPosition().getY();

        return x > (( 2 * canvas.getWidth()) / 3) && y < (canvas.getHeight() / 3);
    }

    private boolean foxyInsideRecThree(){
        double x = foxy.getPosition().getX();
        double y = foxy.getPosition().getY();

        return x < (canvas.getWidth() / 3) && y > (2 * canvas.getHeight()) / 3;
    }

    private boolean foxyInsideRecFour(){
        double x = foxy.getPosition().getX();
        double y = foxy.getPosition().getY();

        return x > (( 2 * canvas.getWidth()) / 3) && y > (2 * canvas.getHeight()) / 3;
    }
}
