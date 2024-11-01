package stardewValley.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

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
    private static final double FOXYSIZE = 75;
    private boolean lookAtRight;

    private int frame;

    private State state;

    //Keys
    private boolean rightPressed;
    private boolean leftPressed;
    private boolean upPressed;
    private boolean downPressed;
    private int scene;


    public Foxy(Canvas canvas) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        this.idlesR = new ArrayList<>();
        this.idlesL = new ArrayList<>();
        this.runsR = new ArrayList<>();
        this.runsL = new ArrayList<>();
        this.state = State.STATIC;
        this.frame = 0;
        this.scene = 1;
        this.position = new Position(100, 100);
        this.lookAtRight = false;
        setIdlesR();
        setRunsR();
        setIdlesL();
        setRunsL();
    }

    private void setIdlesR(){
        for(int i = 1; i <= 4; i++){
            idlesR.add(new Image(getClass().getResourceAsStream("/img/Characters/Foxy/Sprites/idle/idlR/player-idle-" + i +".png"), FOXYSIZE, FOXYSIZE, false, false));
        }
    }

    private void setRunsR(){
        for(int i = 1; i <= 6; i++){
            runsR.add(new Image(getClass().getResourceAsStream("/img/Characters/Foxy/Sprites/run/runR/player-run-" + i +".png"), FOXYSIZE, FOXYSIZE, false, false));
        }
    }

    private void setIdlesL(){
        for(int i = 1; i <= 4; i++){
            idlesL.add(new Image(getClass().getResourceAsStream("/img/Characters/Foxy/Sprites/idle/idlL/player-idle-" + i +".png"), FOXYSIZE, FOXYSIZE, false, false));
        }
    }

    private void setRunsL(){
        for(int i = 1; i <= 6; i++){
            runsL.add(new Image(getClass().getResourceAsStream("/img/Characters/Foxy/Sprites/run/runL/player-run-" + i +".png"), FOXYSIZE, FOXYSIZE, false, false));
        }
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    /*
    public void center(){
        double x = (canvas.getWidth() - FOXYSIZE) / 2;
        double y = (canvas.getHeight() - FOXYSIZE) / 2;

        position.setX(x);
        position.setY(y);
    }
    */

    public void draw() {
        if(scene == 1){
            onMove();
        } else {
            onMove2();
        }

        if (state.equals(State.STATIC)) {
            if(lookAtRight){
                gc.drawImage(idlesR.get(frame % idlesR.size()), position.getX(), position.getY());
            } else {
                gc.drawImage(idlesL.get(frame % idlesL.size()), position.getX(), position.getY());
            }

            frame++;

        } else if (state.equals(State.RUNNING)) {

            if (lookAtRight) {
                gc.drawImage(runsR.get(frame % runsR.size()), position.getX(), position.getY());
            } else {
                gc.drawImage(runsL.get(frame % runsL.size()), position.getX(), position.getY());
            }
            frame++;
        }
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


    /*
    public void onMove(){
        if (upPressed){
            position.setY(position.getY() - 10);
        }
        if (downPressed){
            position.setY(position.getY() + 10);
        }
        if (rightPressed){
            position.setX(position.getX() + 10);
        }
        if (leftPressed){
            position.setX(position.getX() - 10);
        }
    }

     */



    public void onMove(){
        double newX = position.getX();
        double newY = position.getY();

        if (upPressed) {
            newY -= 10;
        }
        if (downPressed) {
            newY += 10;
        }
        if (rightPressed) {
            newX += 10;
        }
        if (leftPressed) {
            newX -= 10;
        }

        // Limita el movimiento dentro del canvas
        double canvasWidth = canvas.getWidth();
        double canvasHeight = canvas.getHeight();

        if (newX < 0) {
            newX = 0;
        } else if (newX + FOXYSIZE > canvasWidth) {
            newX = canvasWidth - FOXYSIZE;
        }

        if (newY < 0) {
            newY = 0;
        } else if (newY + FOXYSIZE > canvasHeight) {
            newY = canvasHeight - FOXYSIZE;
        }

        position.setX(newX);
        position.setY(newY);
    }

    public void onMove2(){
        double newX = position.getX();
        double newY = position.getY();

        if (upPressed) {
            newY -= 10;
        }
        if (downPressed) {
            newY += 10;
        }
        if (rightPressed) {
            newX += 10;
        }
        if (leftPressed) {
            newX -= 10;
        }


        double canvasWidth = canvas.getWidth();
        double canvasHeight = canvas.getHeight();

        if (newX < 0) {
            newX = 0;
        } else if (newX + FOXYSIZE > canvasWidth) {
            newX = canvasWidth - FOXYSIZE;
        }

        if (newY < 0) {
            newY = 0;

        } else if (newY + FOXYSIZE > canvasHeight) {
            newY = canvasHeight - FOXYSIZE;
        }

        /*
        if (newX < 50 && newY < 280) { // Ajusta 50 y 400 según la posición real de la pared A
            newX = 50;
        }

         */

        if (newY <= 115 && newX < 690) { //Esto es para que en la esquina de la pared blanca
                                        // y su pared adyacente Foxy no se salga
            newY = 115;
            newX = 700;
        }

        //Primera pared superior (la pared blanca de la izquierda)
        if (newY < 325 && newX < 690) {
            newY = 325;
        }

        //Primer piso (el piso debajo de la primera pared blanca)
        if (newY > 550 && newX < 690) {
            newY = 550;
        } else if((newX < 700 && newY > 550)) { //Pared iquierda del piso del centro
            newX = 700;
        }

        //Segunda pared superior (la pared blanca de la derecha)
        if (newX >= 690 && newY < 115) {
            newY = 115;
        } else if((newX <= 690 && newY <= 285)) { //Pared adyacente a la segunda pared blanca
            newX = 700;
        }

        //Tercer piso (el piso de la derecha)
        if (newY > 550 && newX > 1160) {
            newY = 550;
        } else if((newX >= 1150 && newY > 550)) { //Pared derecha del piso del centro
            newX = 1150;
        }



        position.setX(newX);
        position.setY(newY);
    }




    public Position getPosition(){
        return position;
    }

    public void setScene(int sc){
        scene = sc;
    }

}
