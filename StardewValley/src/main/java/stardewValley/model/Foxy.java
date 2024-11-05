package stardewValley.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import stardewValley.control.Controller;

import java.util.ArrayList;

public class Foxy {

    //Graphic elements
    private Canvas canvas;
    private GraphicsContext gc;

    private ArrayList<Image> idlesR;
    private ArrayList<Image> idlesL;

    private ArrayList<Image> runsR;
    private ArrayList<Image> runsL;


    private Position position;
    private double foxySizeH;
    private double foxySizeW;
    private boolean lookAtRight;

    private int frame;

    private State state;

    //Keys and boolean
    private boolean rightPressed;
    private boolean leftPressed;
    private boolean upPressed;
    private boolean downPressed;
    private int scene;
    private boolean paint;
    private boolean changePosition;

    //Colisiones
    double newX;
    double newY;
    double canvasWidth;
    double canvasHeight;

    private static final double HEIGHT_PROPORTION = 0.0578703704;
    private static final double WIDTH_PROPORTION = 0.0325520833;


    public Foxy(Canvas canvas) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        initArrayList();
        this.state = State.STATIC;
        this.frame = 0;
        this.scene = 1;
        this.position = new Position(600, 400);
        this.foxySizeW = canvas.getWidth() * WIDTH_PROPORTION;
        this.foxySizeH = canvas.getHeight() * HEIGHT_PROPORTION;
    }

    private void initArrayList(){
        this.idlesR = new ArrayList<>();
        this.idlesL = new ArrayList<>();
        this.runsR = new ArrayList<>();
        this.runsL = new ArrayList<>();
    }

    private ArrayList<Image> loadImages(String basePath, int count) {
        ArrayList<Image> images = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            images.add(new Image(getClass().getResourceAsStream(basePath + i + ".png"), foxySizeW, foxySizeH, false, false));
        }
        return images;
    }

    private void setAnimations() {
        idlesR = loadImages("/img/Characters/Foxy/Sprites/idle/idlR/player-idle-", 4);
        idlesL = loadImages("/img/Characters/Foxy/Sprites/idle/idlL/player-idle-", 4);
        runsR = loadImages("/img/Characters/Foxy/Sprites/run/runR/player-run-", 6);
        runsL = loadImages("/img/Characters/Foxy/Sprites/run/runL/player-run-", 6);
    }


    public void draw() {
        if (!paint) {

            idlesL.clear();
            idlesR.clear();
            runsR.clear();
            runsL.clear();

            this.foxySizeW = canvas.getWidth() * WIDTH_PROPORTION;
            this.foxySizeH = canvas.getHeight() * HEIGHT_PROPORTION;
            setAnimations();
            paint = true;
        }

        onMove();
        ArrayList<Image> currentAnimation = state.equals(State.STATIC) ? (lookAtRight ? idlesR : idlesL) : (lookAtRight ? runsR : runsL);
        gc.drawImage(currentAnimation.get(frame % currentAnimation.size()), position.getX(), position.getY());
        frame++;
    }


    public void onKeyPressed(KeyEvent e) {
        switch (e.getCode()) {
            case UP -> {
                this.upPressed = true;
                this.state = State.RUNNING; }

            case DOWN -> {
                this.downPressed = true;
                this.state = State.RUNNING;}
            case RIGHT -> {
                this.rightPressed = true;
                this.state = State.RUNNING;
                this.lookAtRight = true;}
            case LEFT -> {
                this.leftPressed = true;
                this.state = State.RUNNING;
                this.lookAtRight = false;}
        }

    }

    public void onKeyReleased(KeyEvent e) {
        switch (e.getCode()) {
            case UP -> this.upPressed = false;
            case DOWN -> this.downPressed = false;
            case RIGHT -> this.rightPressed = false;
            case LEFT -> this.leftPressed = false;
        }

        if (!upPressed && !downPressed && !rightPressed && !leftPressed) {
            this.state = State.STATIC;
        }
    }

    public void onMove(){
        if(isChangePosition() == true){
            updatePosition();
        }

        anyScene();

        if(scene == 1){

            double x1 = canvasWidth * 0.46875;
            double x2 = canvasWidth * 0.5013020833;
            double y1 = canvasHeight * 0.4976851852;
            double y2 = canvasHeight * 0.5439814815;

            if((newX > x1 && newX < x2 ) && (newY >= y1 && newY < y2)){
                scene = 2;
                newY = canvas.getHeight() * 0.8564814815;
                newX = canvas.getWidth() * 0.6315104167;
                Controller.SCREEN = 1;
            }

        } else if(scene == 2){

            if(newX  < canvasHeight * 0.04557291667) {

                //Esto se va a cambiar. Es para la escena 3
                scene = 3;
                newX = canvas.getWidth() * 0.4817708333;
                newY = canvas.getHeight() * 0.599537037;
                Controller.SCREEN = 2;
            } else if(newY > canvasHeight * 0.8726851852){
                scene = 1;
                newX = canvas.getWidth() * 0.4817708333;
                newY = canvas.getHeight() * 0.599537037;
                Controller.SCREEN = 0;
            } else {
                collisionsScene2();
            }
        }

        System.out.println("--------------------------");
        System.out.println("Position x:" + newX);
        System.out.println("Position y:" + newY);
        System.out.println("--------------------------");
        System.out.println("Canvas width:" + canvasWidth);
        System.out.println("Canvas height:" + canvasHeight);


        position.setX(newX);
        position.setY(newY);
    }

    //Este metodo es para el movimiento en cualquier escenario
    private void anyScene(){
        newX = position.getX();
        newY = position.getY();

        /*
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

         */

        double my = 0.01157407407 * canvasHeight;
        double mx = 0.006510416667 * canvasWidth;

        if (upPressed) {
            newY -= my;
        }
        if (downPressed) {
            newY += my;
        }
        if (rightPressed) {
            newX += mx;
        }
        if (leftPressed) {
            newX -= mx;
        }

        if (newX < 0) {
            newX = 0;
        } else if (newX + foxySizeH > canvasWidth) {
            newX = canvasWidth - foxySizeH;
        }

        if (newY < 0) {
            newY = 0;
        } else if (newY + foxySizeH > canvasHeight) {
            newY = canvasHeight - foxySizeH;
        }
    }

    public Position getPosition(){
        return position;
    }

    public void setScene(int sc){
        scene = sc;
    }

    public void setPaint(boolean paint  ){
        this.paint = paint;
    }

    public void collisionsScene2(){
        //Primera pared superior (la pared blanca de la izquierda)
        if (newY < VBar.getInstance().getPosition1().getY() && newX < HBar.instance.getPosition1().getX()) {
            newY = VBar.getInstance().getPosition1().getY();
        }
        if (newX < VBar.instance.getPosition1().getX() && newY < VBar.getInstance().getPosition1().getY()) { //Pared adyacente a la segunda pared blanca
            newX = VBar.instance.getPosition1().getX();
        }

        //Primer piso (el piso debajo de la primera pared blanca)
        if (newY > VBar.instance.getPosition3().getY() && newX < HBar.instance.getPosition1().getX()) {
            newY = VBar.instance.getPosition3().getY();
        }
        if (newX < VBar.instance.getPosition2().getX() && newY > VBar.instance.getPosition2().getY()) { //Pared iquierda del piso del centro
            newX = VBar.instance.getPosition2().getX();
        }

        //Segunda pared superior (la pared blanca de la derecha)
        if (newX >= HBar.instance.getPosition2().getX() && newY < HBar.instance.getPosition2().getY()) {
            newY = HBar.instance.getPosition2().getY();
        }

        //Tercer piso (el piso de la derecha)
        if (newY > HBar.instance.getPosition3().getY() && newX > HBar.instance.getPosition3().getX()) {
            newY = HBar.instance.getPosition3().getY() + 1;
        } else if (newX >= VBar.instance.getPosition3().getX() && newY > VBar.instance.getPosition3().getY()) { //Pared derecha del piso del centro
            newX = VBar.instance.getPosition3().getX();
        }
    }

    //Este metodo es para cuando el canva cambie sus dimensiones (para que foxy no se mueva de posición)
    public void updatePosition() {
        //Esto es para cuando el canva se inicialice por primera vez (hablo de las variables como canvasWidth y height)
        if (canvasWidth == 0 || canvasHeight == 0) {
            canvasWidth = canvas.getWidth();
            canvasHeight = canvas.getHeight();
        }

        // Esto es para calcular las proporciones de la posición actual en el canva
        double proportionX = position.getX() / canvasWidth;
        double proportionY = position.getY() / canvasHeight;

        // Con esto se actualizan las variables
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();


        if (canvasWidth != 0 && canvasHeight != 0) {
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



    public void setChangePosition(boolean changePosition){
        this.changePosition = changePosition;
    }

    public boolean isChangePosition(){
        return changePosition;
    }

}
