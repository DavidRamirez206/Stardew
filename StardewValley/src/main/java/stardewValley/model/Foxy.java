package stardewValley.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import stardewValley.control.Controller;

import java.util.ArrayList;
import java.util.Random;

public class Foxy extends CharacterMove {

    //Graphic elements

    private double score;
    private int stones, wood;


    private ArrayList<Image> climb;

    private ArrayList<Image> idlesL, idlesL_WithAxe, idlesL_WithPickAxe;
    private ArrayList<Image> idlesR, idlesR_WithAxe, idlesR_WithPickAxe;

    private ArrayList<Image> runsL, runsL_WithAxe, runsL_WithPickAxe;
    private ArrayList<Image> runsR, runsR_WithAxe, runsR_WithPickAxe;

    private ArrayList<Image> hurt;

    private boolean cutting, isAxe, isPick;

    private State state, state2;
    private boolean toolTaken;

    private boolean spacePressed;

    private Axe axe;
    private Pickaxe pickaxe;

    private final double HEIGHT_PROPORTION = 0.0578703704;
    private final double WIDTH_PROPORTION = 0.0325520833;

    public Foxy(Canvas canvas, Axe axe, Pickaxe pickaxe) {
        super(canvas);
        this.axe = axe;
        this.pickaxe = pickaxe;
        this.health = 100;
        //initArrayList();
        this.state = State.STATIC;
        this.esc = 1;
        this.width = canvas.getWidth() * WIDTH_PROPORTION;
        this.height = canvas.getHeight() * HEIGHT_PROPORTION;
    }

    @Override
    protected void initArrayList(){
        this.idlesR = new ArrayList<>();
        this.idlesR_WithAxe = new ArrayList<>();
        this.idlesR_WithPickAxe = new ArrayList<>();

        this.idlesL = new ArrayList<>();
        this.idlesL_WithAxe = new ArrayList<>();
        this.idlesL_WithPickAxe = new ArrayList<>();

        this.runsR = new ArrayList<>();
        this.runsR_WithAxe = new ArrayList<>();
        this.runsR_WithPickAxe = new ArrayList<>();

        this.runsL = new ArrayList<>();
        this.runsL_WithAxe = new ArrayList<>();
        this.runsL_WithPickAxe = new ArrayList<>();

        this.climb = new ArrayList<>();
        this.hurt = new ArrayList<>();
    }

    private void setAnimations() {
        climb = loadImages("/img/Characters/Foxy/Sprites/climb/player-climb-", 3);
        hurt = loadImages("/img/Characters/Foxy/Sprites/hurt/player-hurt-", 2);
        loadIdles();
        loadRuns();
    }

    private void loadIdles(){
        idlesR = loadImages("/img/Characters/Foxy/Sprites/idle/idlR/player-idle-", 4);
        idlesR_WithAxe = loadImages("/img/Characters/Foxy/Sprites/idle/idlR_StoneAxe/player-idle-", 4);
        idlesR_WithPickAxe = loadImages("/img/Characters/Foxy/Sprites/idle/idlR_StonePickAxe/player-idle-", 4);

        idlesL = loadImages("/img/Characters/Foxy/Sprites/idle/idlL/player-idle-", 4);
        idlesL_WithAxe = loadImages("/img/Characters/Foxy/Sprites/idle/idlL_StoneAxe/player-idle-", 4);
        idlesL_WithPickAxe = loadImages("/img/Characters/Foxy/Sprites/idle/idlL_StonePickAxe/player-idle-", 4);
    }

    private void loadRuns(){
        runsR = loadImages("/img/Characters/Foxy/Sprites/run/runR/player-run-", 6);
        runsR_WithAxe = loadImages("/img/Characters/Foxy/Sprites/run/runR_StoneAxe/player-run-", 6);
        runsR_WithPickAxe = loadImages("/img/Characters/Foxy/Sprites/run/runR_StonePickAxe/player-run-", 6);

        runsL = loadImages("/img/Characters/Foxy/Sprites/run/runL/player-run-", 6);
        runsL_WithAxe = loadImages("/img/Characters/Foxy/Sprites/run/runL_StoneAxe/player-run-", 6);
        runsL_WithPickAxe = loadImages("/img/Characters/Foxy/Sprites/run/runL_StonePickAxe/player-run-", 6);
    }

    private void idlesClear(){
        idlesR.clear();
        idlesR_WithAxe.clear();
        idlesR_WithPickAxe.clear();

        idlesL.clear();
        idlesL_WithAxe.clear();
        idlesL_WithPickAxe.clear();
    }

    private void runsClear(){
        runsR.clear();
        runsR_WithAxe.clear();
        runsR_WithPickAxe.clear();

        runsL.clear();
        runsL_WithAxe.clear();
        runsL_WithPickAxe.clear();
    }

    private void updateAnimations() {
        idlesClear();
        runsClear();
        climb.clear();
        hurt.clear();

        this.width = canvas.getWidth() * WIDTH_PROPORTION;
        this.height = canvas.getHeight() * HEIGHT_PROPORTION;
        setAnimations();
        paint = true;
    }

    private ArrayList<Image> animations(){
        ArrayList<Image> images = new ArrayList<>();
        if(state.equals(State.STATIC)){

            if(toolTaken){
                if(lookAtRight){
                    switch(state2){
                        case WITH_AXE -> {
                            images = idlesR_WithAxe;
                        }
                        case WITH_PICKAXE -> {
                            images = idlesR_WithPickAxe;
                        }
                    }
                } else {
                    switch(state2){
                        case WITH_AXE -> {
                            images = idlesL_WithAxe;
                        }
                        case WITH_PICKAXE -> {
                            images = idlesL_WithPickAxe;
                        }
                    }
                }
            } else {
                if(lookAtRight){
                    images = idlesR;
                } else {
                    images = idlesL;
                }
            }

        } else if (state.equals(State.RUNNING)){
            if(toolTaken){
                if(lookAtRight){
                    switch(state2){
                        case WITH_AXE -> {
                            images = runsR_WithAxe;
                        }
                        case WITH_PICKAXE -> {
                            images = runsR_WithPickAxe;
                        }
                    }
                } else {
                    switch(state2){
                        case WITH_AXE -> {
                            images = runsL_WithAxe;
                        }
                        case WITH_PICKAXE -> {
                            images = runsL_WithPickAxe;
                        }
                    }
                }
            } else {
                if(lookAtRight){
                    images = runsR;
                } else {
                    images = runsL;
                }
            }
        } else if (state.equals(State.CLIMBING)){
            images = climb;
        } else if(state.equals(State.CUTTING) && toolTaken){
            images = hurt;
        }

        return images;
    }

    @Override
    public void draw() {

        if (!paint) {
            updateAnimations();
        }
        if(isAxe || isPick){
            if(axe.getDurability() == 0 && isAxe){
                isAxe = false;
            }

            if(pickaxe.getDurability() == 0 && isPick){
                isPick = false;
            }

            if(!isPick && !isAxe){
                toolTaken = false;
                state = State.STATIC;
            } else {
                if(isAxe && !isPick){
                    state2 = State.WITH_AXE;
                } else if(isPick && !isAxe){
                    state2 = State.WITH_PICKAXE;
                }
            }
        }

        onMove();
        ArrayList<Image> currentAnimation = animations();

        gc.drawImage(currentAnimation.get(frame % currentAnimation.size()), position.getX(), position.getY());
        frame++;
    }


    public void onKeyPressed(KeyEvent e) {
        switch (e.getCode()) {
            case UP -> {
                this.up = true;
                this.state = State.RUNNING;
                if(esc == 3){
                    if(climb1() == true) {
                        this.state = State.CLIMBING;
                    }
                }
                }

            case DOWN -> {
                this.down = true;
                this.state = State.RUNNING;
                if(esc == 3){
                    if(climb1() == true) {
                        this.state = State.CLIMBING;
                    }
                }
            }

            case RIGHT -> {
                this.right = true;
                this.state = State.RUNNING;
                this.lookAtRight = true;
            }

            case LEFT -> {
                this.left = true;
                this.state = State.RUNNING;
                this.lookAtRight = false;
            }

            case F -> {
                if(toolTaken){
                    spacePressed = true;
                    state = State.CUTTING;
                    cutting = true;
                }

            }

            case DIGIT1 -> {
                state = State.STATIC;
                toolTaken = false;
            }

            case DIGIT2 -> {
                if(isAxe && axe.getDurability() > 0){
                    toolTaken = true;
                    state2 = State.WITH_AXE;
                }
            }

            case DIGIT3 -> {
                if(isPick && pickaxe.getDurability() > 0){
                    toolTaken = true;
                    state2 = State.WITH_PICKAXE;
                }
            }

            case R -> {
                if(toolTaken && state2.equals(State.WITH_AXE)){
                    if(stones > 10 && wood > 10 && axe.getDurability() != 100){
                        wood -= 10;
                        stones -= 10;
                        axe.repair();
                    }
                } else if (toolTaken && state2.equals(State.WITH_PICKAXE)) {
                    if(stones > 10 && wood > 15 && pickaxe.getDurability() != 100){
                        stones -= 10;
                        wood -= 15;
                        pickaxe.repair();
                    }
                }
            }
        }

    }

    public void onKeyReleased(KeyEvent e) {
        switch (e.getCode()) {
            case UP -> this.up = false;
            case DOWN -> this.down = false;
            case RIGHT -> this.right = false;
            case LEFT -> this.left = false;
            case F -> {
                this.cutting = false;
                this.spacePressed = false;
            }
        }

        if (!up && !down && !right && !left && !spacePressed) {
            this.state = State.STATIC;
        }

        if(esc == 3){
            if(climb1() == true) {
                this.state = State.CLIMBING;
            }
        }
    }

    private void collisionsHouse(){
        //Vertical 1
        fromTo(0.4427083333, 0.4143518519, 0.4427083333, 0.5555555554);

        //Vertical 2
        fromTo(0.521484375, 0.4143518519, 0.52734375, 0.5555555554);

        //Horizontal 1
        fromTo(0.4427083333, 0.4143518519, 0.525390625, 0.4143518519);

        //Horizontal 1/2 (A)
        fromTo(0.4427083333, 0.5555555554, 0.46875, 0.5555555554);

        //Horizontal 2/2 (B) 0.52734375 - 0.521484375
        fromTo(0.5013020833, 0.5555555554, 0.525390625, 0.5555555554);
    }

    @Override
    public void onMove(){
        if(isChangePosition() == true){
            updatePosition();
        }

        anyScene();

        if(esc == 1){

            double x1 = canvasWidth * 0.46875;
            double x2 = canvasWidth * 0.5013020833;
            double y1 = canvasHeight * 0.4976851852;
            double y2 = canvasHeight * 0.5439814815;

            if((newX > x1 && newX < x2 ) && (newY >= y1 && newY < y2)){

                esc = 2;
                newY = canvas.getHeight() * 0.8564814815;
                newX = canvas.getWidth() * 0.6315104167;
                Controller.SCREEN = 1;


                System.out.println(canvas.getHeight() * 0.8564814815);
                System.out.println(canvas.getWidth() * 0.6315104167);
            } else {
                collisionsHouse();
            }

        } else if(esc == 2){

            if(newX  < canvasWidth * 0.01953125) { //Scene3
                esc = 3;
                newX = canvas.getWidth() * 0.0462962963;
                newY = canvas.getHeight() * 0.5960648148;
                lookAtRight = true;
                Controller.SCREEN = 2;

            } else if(newY > canvasHeight * 0.8726851852){ //Scene 1
                esc = 1;
                newX = canvas.getWidth() * 0.4817708333;
                newY = canvas.getHeight() * 0.599537037;
                Controller.SCREEN = 0;
            } else {
                generalCollisionsInScene2();
            }
        } else {
            if(newX <= 0 && ((newY >= canvasHeight * 0.5555555557) && (newY <= canvasHeight * 0.6111111111))){
                esc = 2;
                newX = canvasWidth * 0.08463541667;
                newY = canvasHeight * 0.5902777779;
                lookAtRight = true;
                Controller.SCREEN = 1;
            } else {

                climbScene3();
                diagonalCollisions3();
            }
        }

        /*
        System.out.println("--------------------------");
        System.out.println("Position x:" + newX);
        System.out.println("Position y:" + newY);
        System.out.println("--------------------------");
        System.out.println("Canvas width:" + canvasWidth);
        System.out.println("Canvas height:" + canvasHeight);

         */

        position.setX(newX);
        position.setY(newY);
    }

    public void onMove2(double x1, double y1, double x2, double y2){
        fromTo(x1, y1, x2, y2);
        onMove();
    }

    @Override
    protected void updatePosition2() {

        if(isFirstPosition){
            position.setX(canvasWidth * 0.5);
            position.setY(0);
            System.out.println("Posición Foxy: (" + position.getX() + ", " + position.getY() + ")");
            System.out.println("Canvas width:" + canvasWidth);
            System.out.println("Canvas height:" + canvasHeight);
            isFirstPosition = false;
        } else if (canvasWidth != 0 && canvasHeight != 0) {
            // Lo siguiente es para ajustar la posición basándose en las nuevas dimensiones del canvas
            position.setX(proportionX * canvasWidth);
            position.setY(proportionY * canvasHeight);
        } else {
            // Esto es por si acaso lo anterior falla
            position.setX(0);
            position.setY(0);
        }

        changePosition = false;
    }

    public void climbScene3(){
        if(newX < 0.05208333334 * canvasWidth && (newY < canvasHeight * 0.4861111109 && newY > canvasHeight * 0.3587962961)){
            newY = canvasHeight * 0.4861111109;
        }
    }

    public boolean climb1(){
        if((position.getX() >= canvasWidth * 0.05208333334 && position.getX() <= canvasWidth * 0.08463541667) &&
            (position.getY() >= canvasHeight * 0.3530092593 && position.getY() <= canvasHeight * 0.5381944444)){
            return true;
        }

        return false;
    }

    public void diagonalCollisions3(){
        diagonalCollisionsBR();
        diagonalCollisionsSR();
        diagonalCollisionsMA();
        horizontalCollisions3();
    }

    public void diagonalCollisionsBR() {
        //Big River
        //Diagonal 1
        fromTo(0.3002025463, 0.7581018518, 0.326244213, 0.7233796296);
        //Diagonal 2
        fromTo(0.4759837963, 0.7233796296, 0.541087963, 0.5960648148);
        //Diagonal 3
        fromTo(0.5671296297, 0.5960648148, 0.619212963, 0.4745370369);
        //Diagonal 4
        fromTo(0.677806713, 0.4745370369, 0.7298900463, 0.3472222221);
        //Diagonal 5
        fromTo(0.7819733797, 0.3472222221, 0.8080150463, 0.3993055556);
        //Diagonal 6
        fromTo(0.834056713, 0.3993055556, 0.8991608797, 0.4918981482);
        //Diagonal 7
        fromTo(0.4791666666, 0.8148148148, 0.5247395833, 1);
    }

    public void diagonalCollisionsSR() {
    }

    public void diagonalCollisionsMA(){
        //Mountain A
        //Diagonal 8
        fromTo(0.0853587963, 0.4918981482, 0.130931713, 0.4456018519);
        //Diagonal 9
        fromTo(0.228587963, 0.3645833334, 0.267650463, 0.306712963);
        //Diagonal 10
        fromTo(0.2220775463, 0.09259259256, 0.2611400463, 0.1388888888);
        fromTo(0.2220775463, 0.09837962979, 0.1634837963, 0);
    }

    private void horizontalCollisions3(){
        fromTo(0, 0.7523148146, 0.130931713, 0.7523148146);
        fromTo(0, 0.8101851852, 0.130931713, 0.8101851852);

        fromTo(0.169994213, 0.7523148146, 0.2994791667, 0.7523148146);
        fromTo(0.169994213, 0.8101851852, 0.3718171296, 0.8101851852);

        fromTo(0.3255208333, 0.7233796296, 0.3645833334, 0.7233796296);

        fromTo(0.4036458334, 0.7233796296, 0.48828125, 0.7233796296);
        fromTo(0.4036458334, 0.8159722221, 0.48828125, 0.8159722221);

        fromTo(0.5338541667, 0.6076388889, 0.5794270834, 0.6076388889);
        fromTo(0.6184895834, 0.46875, 0.6770833334, 0.46875);

        fromTo(0.91796875, 0.1678240742, 1, 0.1678240742);
        fromTo(0.8919270834, 0.4803240741, 1, 0.4803240741);

        fromTo(0.4231770834, 0.1041666666, 0.3190104167, 0.1041666666);
        fromTo(0.2669270833, 0.1736111111, 0.3190104167, 0.1736111111);

        //Piedras del río
        fromTo(0.7747395834, 0.3530092593, 0.7747395834, 0.6539351852);
        fromTo(0.736400463, 0.3530092593, 0.736400463, 0.6539351852);

        fromTo(0.4075520833, 0.7175925923, 0.4075520833, 0.8101851852);
        fromTo(0.3684895833, 0.7175925923, 0.3684895833, 0.8101851852);

        fromTo(0.1666666666, 0.7638888889, 0.1666666666, 0.8101851852);
        fromTo(0.1276041666, 0.7638888889, 0.1276041666, 0.8101851852);
    }

    public void setState2(int i){
        this.state2 = State.values()[i];
    }

    public void setToolTaken(boolean toolTaken){
        this.toolTaken = toolTaken;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score += score;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public int getStones() {
        return stones;
    }

    public void setStones(int stones) {
        this.stones += stones;
    }

    public int getWood() {
        return wood;
    }

    public void setWood(int wood) {
        this.wood += new Random().nextInt(wood) + 1;
    }


    public double getToolDurability() {
        if(toolTaken){
            switch (state2){
                case WITH_AXE -> {
                    return axe.getDurability();
                }

                case WITH_PICKAXE -> {
                    return pickaxe.getDurability();
                }
            }
        }

        return 0;
    }

    public boolean isCutting(){
        if(toolTaken){
            return cutting;
        } else {
            return false;
        }
    }

    public Axe getAxe() {
        return axe;
    }

    public Pickaxe getPickaxe() {
        return pickaxe;
    }

    public boolean isAxe(){
        return isAxe;
    }

    public void setAxe(boolean isAxe) {
        this.isAxe = isAxe;
    }

    public boolean isPick(){
        return isPick;
    }

    public void setPick(boolean isPick) {
        this.isPick = isPick;
    }

    public String getStringTool(){
        if(toolTaken){
            switch (state2){
                case WITH_AXE -> {
                    return "axe";
                }

                case WITH_PICKAXE -> {
                    return "pickaxe";
                }
            }
        }
       return "";
    }

    public String toolEquipped(){
        if(isPick && isAxe){
            return "Pickaxe and Axe";
        } else {
            if(isPick){
                return "pickaxe";
            }

            if(isAxe){
                return "Axe";
            }
        }


        return "";
    }
}
