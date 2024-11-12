package stardewValley.screens;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import stardewValley.control.Controller;
import stardewValley.model.*;

import java.util.ArrayList;
import java.util.List;

public class ScreenC extends SceneBase{

    private PurplePortal portal;
    private Rock rockE, rockD, rockC, rockB, rockA;
    private Pickaxe pickaxe;
    private Axe axe;
    private boolean axT, pAxT;

    private List<Tree> trees;
    private final int N_TREES = 6;
    private Position[] positions = {
            new Position(0.130931713, 0.1099537039),
            new Position(0.2611400463, 0.6886574074),
            new Position(0.4499421297, 0.5034722222),

            new Position(0.6647858797, 0.2372685186),
            new Position(0.3718171296, 0),
            new Position(0.7624421297, 0),
    };

    //For pickAxe
    private final double X1 = 0.1765046297333324;
    private final double X2 = 0.2350983796429998;
    private final double Y1 = 0.1099537038599992;
    private final double Y2 = 0.1909722223499992;

    //For Axe
    private final double W1 = 0.853587963;
    private final double W2 = 0.912181713;
    private final double Z1 = 0;
    private final double Z2 = 0.04629629628;

    public ScreenC(Canvas canvas, String img, Foxy foxy) {
        super(canvas, img, foxy);
        load(canvas);
        tools(canvas);
        portal.setYPosition(0.5902777776);
        portal.setNewSizeH(1.5);
        portal.setNewSizeW(1);
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
    public void draw(){
        super.updateCanvas();
    }

    public void redraw(){
        super.gcUpdate();
        portal.draw();
        drawRock();
        drawPickaxe();
        drawAxe();
        drawTree2();
        super.foxyRedraw();
        if(!pAxT){
            pickaxeTaken();
        }
        if(!axT){
            axeTaken();
        }
        drawTree1();
    }

    @Override
    public void updateObjects(){
        this.portal.setPaint(false);
        reloadRocks(false);
        reloadTools(false);
        updateTrees();
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
        rockA = new Rock(canvas, 0.5013020834, 0.1967592592, 0, 0.02604166667, 0.0462962963);
        rockB = new Rock(canvas, 0.2799479167, 0.09259259256, 0, 0.03255208333, 0.05787037037);
        rockC = new Rock(canvas, 0.8203125, 0.3703703702, 0, 0.0390625, 0.06944444444);
        rockD = new Rock(canvas, 0.3190104167, 0.4745370369, 0, 0.04557291667, 0.08101851852);
        rockE = new Rock(canvas, 0.05208333333, 0.1504629629, 5, 0.05208333333, 0.09259259259);
    }

    private void drawRock(){
        rockA.otherDraw();
        rockB.otherDraw();
        rockC.otherDraw();
        rockD.otherDraw();
        rockE.otherDraw();
    }

    private void reloadRocks(boolean reload){
        rockA.setPaint(reload);
        rockB.setPaint(reload);
        rockC.setPaint(reload);
        rockD.setPaint(reload);
        rockE.setPaint(reload);
    }

    private void tools(Canvas canvas){
        this.axe = new Axe(canvas, "/img/object/Scene3/tools/StoneAxe", 0.8984375, 0.03472222221, 0.03255208333, 0.05787037037);
        this.pickaxe = new Pickaxe(canvas, "/img/object/Scene3/tools/StonePickaxe", 0.21484375, 0.162037037, 0.03255208333, 0.05787037037);
    }

    private void drawPickaxe(){
        if (!pAxT){
            this.pickaxe.draw1();
        }
    }

    private void drawAxe(){
        if (!axT){
            this.axe.draw1();
        }
    }

    private void reloadTools(boolean reload){
        this.axe.setPaint(reload);
        this.pickaxe.setPaint(reload);
    }

    private void pickaxeTaken(){
        double positionX = foxy.getPosition().getX();
        double positionY = foxy.getPosition().getY();
        double width = canvas.getWidth();
        double height = canvas.getHeight();

        if((positionX > (width * X1) && positionX < width * X2) && (positionY > (height * Y1) && positionY < height * Y2)){
            if(!foxy.getToolTaken()){
                pAxT = true;
                foxy.setToolTaken(true);
                foxy.setState2(6);
            }
        }
    }

    private void axeTaken(){
        double positionX = foxy.getPosition().getX();
        double positionY = foxy.getPosition().getY();
        double width = canvas.getWidth();
        double height = canvas.getHeight();

        if((positionX > (width * W1) && positionX < width * W2) && (positionY > (height * Z1) && positionY < height * Z2)){
            if(!foxy.getToolTaken()){
                axT = true;
                foxy.setToolTaken(true);
                foxy.setState2(5);
            }
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

}
