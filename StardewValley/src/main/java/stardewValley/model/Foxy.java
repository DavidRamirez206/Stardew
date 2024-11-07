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

    private ArrayList<Image> climb;

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
        this.climb = new ArrayList<>();
    }

    private ArrayList<Image> loadImages(String basePath, int count) {
        ArrayList<Image> images = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            images.add(new Image(getClass().getResourceAsStream(basePath + i + ".png"), foxySizeW, foxySizeH, false, false));
        }
        return images;
    }

    private void setAnimations() {
        climb = loadImages("/img/Characters/Foxy/Sprites/climb/player-climb-", 3);
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
            climb.clear();

            this.foxySizeW = canvas.getWidth() * WIDTH_PROPORTION;
            this.foxySizeH = canvas.getHeight() * HEIGHT_PROPORTION;
            setAnimations();
            paint = true;
        }

        onMove();
        ArrayList<Image> currentAnimation = new ArrayList<>();
        if(state.equals(State.STATIC)){
            if(lookAtRight == true){
                currentAnimation = idlesR;
            } else {
                currentAnimation = idlesL;
            }
        } else if(state.equals(State.RUNNING)){
            if(lookAtRight == true){
                currentAnimation = runsR;
            } else {
                currentAnimation = runsL;
            }
        } else if(state.equals(State.CLIMBING)) {
            currentAnimation = climb;
        }

        gc.drawImage(currentAnimation.get(frame % currentAnimation.size()), position.getX(), position.getY());
        frame++;
    }


    public void onKeyPressed(KeyEvent e) {
        switch (e.getCode()) {
            case UP -> {
                this.upPressed = true;
                this.state = State.RUNNING;
                if(scene == 3){
                    if(climb1() == true) {
                        this.state = State.CLIMBING;
                    }
                }
                }

            case DOWN -> {
                this.downPressed = true;
                this.state = State.RUNNING;
                if(scene == 3){
                    if(climb1() == true) {
                        this.state = State.CLIMBING;
                    }
                }
            }

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

        if(scene == 3){
            if(climb1() == true) {
                this.state = State.CLIMBING;
            }
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

            //0.04557291667
            //0.01953125
            if(newX  < canvasWidth * 0.01953125) { //Scene3
                scene = 3;
                newX = canvas.getWidth() * 0.0462962963;
                newY = canvas.getHeight() * 0.5960648148;
                lookAtRight = true;
                Controller.SCREEN = 2;

                /*
                //Esto se va a cambiar. Es para la escena 3
                scene = 3;
                newX = canvas.getWidth() * 0.4817708333;
                newY = canvas.getHeight() * 0.599537037;
                Controller.SCREEN = 2;

                 */
            } else if(newY > canvasHeight * 0.8726851852){ //Scene 1
                scene = 1;
                newX = canvas.getWidth() * 0.4817708333;
                newY = canvas.getHeight() * 0.599537037;
                Controller.SCREEN = 0;
            } else {
                collisionsScene2();
            }
        } else {
            if(newX <= 0 && ((newY >= canvasHeight * 0.5555555557) && (newY <= canvasHeight * 0.6111111111))){
                scene = 2;
                newX = canvasWidth * 0.08463541667;
                newY = canvasHeight * 0.5902777779;
                lookAtRight = true;
                Controller.SCREEN = 1;
            } else {

                climbScene3();
                diagonalCollisions();
                // Aquí se actualizan las posiciones newX y newY para evitar que pase el río
                if (newX == position.getX() && newY == position.getY()) {
                    // Foxy intenta cruzar el río, bloquea el movimiento
                    System.out.println("Foxy no puede cruzar el río!");
                }
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

    public void fromTo(double x1, double y1, double x2, double y2) {
        // Coordenadas de la línea diagonal (río)

        x1 *= canvasWidth;
        y1 *= canvasHeight;
        x2 *= canvasWidth;
        y2 *= canvasHeight;

        /*
        1
         = 411.11111113420765;
         = 265.0000000751993;

         2
         = 291.1111111280638;
         = 375.0000000364796;

         */


         //||
        //intersectsLine(newX, newY, position.getX(), position.getY(), x1, y1, x2, y2)
        // Verificar si Foxy intenta cruzar el río
        if (intersectsLine(position.getX(), position.getY(), newX, newY, x1, y1, x2, y2)) {
            // Si hay intersección, ajustar las posiciones para que no cruce el río
            newX = position.getX();
            newY = position.getY();
        }
    }

    private boolean intersectsLine(double x0, double y0, double x1, double y1, double x2, double y2, double x3, double y3) {
        double s1_x, s1_y, s2_x, s2_y;
        s1_x = x1 - x0;
        s1_y = y1 - y0;
        s2_x = x3 - x2;
        s2_y = y3 - y2;

        double s, t;
        s = (-s1_y * (x0 - x2) + s1_x * (y0 - y2)) / (-s2_x * s1_y + s1_x * s2_y);
        t = ( s2_x * (y0 - y2) - s2_y * (x0 - x2)) / (-s2_x * s1_y + s1_x * s2_y);

        return s >= 0 && s <= 1 && t >= 0 && t <= 1;
    }

    public void diagonalCollisions(){
        //River
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
        //Diagonal 7 0.9421296296
        fromTo(0.4791666666, 0.8148148148, 0.5247395833, 1);

        //Mountain A
        //Diagonal 8
        fromTo(0.0853587963, 0.4918981482, 0.130931713, 0.4456018519);
        //Diagonal 9
        fromTo(0.228587963, 0.3645833334, 0.267650463, 0.306712963);
        //Diagonal 10
        fromTo(0.2220775463, 0.09259259256, 0.2611400463, 0.1388888888);

    }



}
