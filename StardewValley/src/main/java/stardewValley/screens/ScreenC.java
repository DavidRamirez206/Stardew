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
    private Pickaxe pickaxe;
    private Axe axe;
    private boolean axT, pAxT;

    private List<Tree> trees;
    private List<Rock> rocks;
    private final int N_TREES = 6;
    private final int N_ROCKS = 5;
    private Position[] positionsTrees= {
            new Position(0.130931713, 0.1099537039),
            new Position(0.2611400463, 0.6886574074),
            new Position(0.4499421297, 0.5034722222),

            new Position(0.6647858797, 0.2372685186),
            new Position(0.3718171296, 0),
            new Position(0.7624421297, 0),
    };

    private Position[] positionsRocks= {
            new Position(0.5013020834, 0.1967592592),
            new Position(0.2799479167, 0.09259259256),
            new Position(0.8203125, 0.3703703702),

            new Position(0.3190104167, 0.4745370369),
            new Position(0.05208333333, 0.1504629629),
    };

    private double[] rocksWidth= {
            0.02604166667,
            0.03255208333,
            0.0390625,
            0.04557291667,
            0.05208333333
    };

    private double[] rocksHeight= {
            0.0462962963,
            0.05787037037,
            0.06944444444,
            0.08101851852,
            0.09259259259
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

    public ScreenC(Canvas canvas, String img, Foxy foxy, Axe axe, Pickaxe pickaxe) {
        super(canvas, img, foxy);
        this.axe = axe;
        this.pickaxe = pickaxe;
        load(canvas);
        //tools(canvas);
        loadTrees();
        loadRocks();
        draw();
    }

    private void loadTrees(){
        this.trees = new ArrayList<>();
        for (int i = 0; i < N_TREES; i++) {
            Position position = positionsTrees[i];
            trees.add(new Tree(canvas, position.getX(), position.getY(), 0, 0.1302083332, 0.1157407408));
        }
    }

    private void loadRocks(){
        this.rocks = new ArrayList<>();
        for (int i = 0; i < N_ROCKS; i++) {
            Position position = positionsRocks[i];
            double width = rocksWidth[i];
            double height = rocksHeight[i];
            rocks.add(new Rock(canvas, position.getX(), position.getY(), 0, width, height));
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

        checkForCuttingTree();
        checkForCuttingRocks();
    }

    @Override
    public void updateObjects(){
        this.portal.setPaint(false);
        reloadRocks();
        reloadTools(false);
        updateTrees();
    }

    @Override
    public void onKeyPressed(KeyEvent event){
        super.foxy.onKeyPressed(event);

        if (event.getCode() == KeyCode.SPACE){
            super.foxy.setEsc(1);
            Controller.SCREEN = 0;
        }
    }

    @Override
    public void onKeyReleased(KeyEvent event){
        super.foxy.onKeyReleased(event);
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

    private void load(Canvas canvas){
        portal = new PurplePortal(canvas, 0, 0.5902777776,1, 1.5);
    }

    private void drawRock(){
        for (Rock rock : rocks) {
            if(!rock.getCut()){
                rock.otherDraw();
            }
        }
    }

    private void reloadRocks(){
        for (Rock rock : rocks){
            rock.setPaint(false);
        }
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
            pAxT = true;
            foxy.setToolTaken(true);
            foxy.setPick(true);
            foxy.setState2(6);
        }
    }

    private void axeTaken(){
        double positionX = foxy.getPosition().getX();
        double positionY = foxy.getPosition().getY();
        double width = canvas.getWidth();
        double height = canvas.getHeight();

        if((positionX > (width * W1) && positionX < width * W2) && (positionY > (height * Z1) && positionY < height * Z2)){
            axT = true;
            foxy.setToolTaken(true);
            foxy.setAxe(true);
            foxy.setState2(5);
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

    public void checkForCuttingTree() {
        String tool = foxy.getStringTool();

        for (Tree tree : trees) {
            if(!tree.getCut() &&
                    tree.handleCutting(foxy.getPosition(), foxy.getWidth(), foxy.getHeight(), tool, foxy.isCutting() && foxy.isAxe())){

                foxy.getAxe().setDurability();
                tree.setKicked(1);

                if(tree.getKicked() == 5){
                    foxy.setWood(40);
                    tree.setCut(true);
                }
            }

        }
    }

    public void checkForCuttingRocks() {
        String tool = foxy.getStringTool();
        for (Rock rock : rocks) {
            if(!rock.getCut() &&
                    rock.handleCutting(foxy.getPosition(), foxy.getWidth(), foxy.getHeight(), tool, foxy.isCutting())){

                foxy.getPickaxe().setDurability();
                rock.setKicked(1);

                if (rock.getKicked() == 10){
                    foxy.setStones(40);
                    rock.setCut(true);
                }
            }
        }
    }

}
