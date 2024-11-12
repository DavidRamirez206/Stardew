package stardewValley.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Random;

public abstract class Obstacle {
    // Graphic elements
    protected Canvas canvas;
    protected GraphicsContext gc;
    protected Image half1, half2;
    protected Position position, position2;
    protected double sizeW, sizeH, sizeW2, sizeH2, positionX, positionY, sW, sH;
    protected boolean paint, paint2, cut;

    protected String basePath;
    protected String basePath2;

    protected int type;

    public Obstacle(Canvas canvas, String basePath, String basePath2, double positionX, double positionY, double sW, double sH) {
        this.canvas = canvas;
        this.sW = sW;
        this.sH = sH;
        this.positionX = positionX;
        this.positionY = positionY;
        this.basePath = basePath;
        this.basePath2 = basePath2;
        this.gc = canvas.getGraphicsContext2D();
        this.position = new Position(100, 100);
        this.position2 = new Position(100, 100);
    }

    public Obstacle(Canvas canvas, String basePath, double positionX, double positionY, double sW, double sH) {
        this.canvas = canvas;
        this.sW = sW;
        this.sH = sH;
        this.positionX = positionX;
        this.positionY = positionY;
        this.basePath = basePath;
        this.gc = canvas.getGraphicsContext2D();
        this.position = new Position(100, 100);
    }

    public Obstacle(Canvas canvas, double positionX, double positionY, int type, int nObjects, double sW, double sH) {
        this.canvas = canvas;
        this.sH = sH;
        this.sW = sW;
        this.gc = canvas.getGraphicsContext2D();
        this.positionX = positionX;
        this.positionY = positionY;
        ramdonType(type, nObjects);
        this.position = new Position(100, 100);
        this.position2 = new Position(100, 100);
    }

    private void ramdonType(int type, int n){
        if(type != 0){
            this.type=type;
        } else {
            this.type = new Random().nextInt(n) + 1; //Without n + 1: 0 until n ---- With n + 1: 1 until n
        }
    }

    protected Image loadImage(String path, double sizeW, double sizeH) {
        return new Image(getClass().getResourceAsStream(path + ".png"), sizeW, sizeH, false, false);
    }

    protected void load1() {
        half1 = loadImage(basePath, sizeW, sizeH);
    }

    protected void load2() {
        half2 = loadImage(basePath2, sizeW2, sizeH2);
    }

    protected abstract void otherLoad();

    protected void adjustSize() {
        sizeW = canvas.getWidth() * sW;
        sizeH = canvas.getHeight() * sH;
        sizeW2 = sizeW;
        sizeH2 = sizeH;

        position.setX((canvas.getWidth() * positionX) - (sizeW / 2));
        position.setY(canvas.getHeight() * positionY);

        if(position2 != null){
            position2.setX(position.getX());

            position2.setY(position.getY() + sizeH);
        }


    }

    protected void adjustSize2() {

        sizeW = canvas.getWidth() * sW;
        sizeH = canvas.getHeight() * sH;

        position.setX((canvas.getWidth() * positionX) - (sizeW / 2));
        position.setY(canvas.getHeight() * positionY);
    }

    public void draw1() {
        if (!paint) {
            adjustSize();
            load1();
            paint = true;
        }
        gc.drawImage(half1, position.getX(), position.getY());
    }

    public void draw2() {
        if (!paint2) {
            adjustSize();
            load2();
            paint2 = true;
        }
        gc.drawImage(half2, position2.getX(), position2.getY());
    }

    public abstract void otherDraw();
    public abstract void otherDraw2();


    public void  setPaint(boolean b){
        this.paint = b;
        this.paint2 = b;
    }

    public boolean getCut(){
        return cut;
    }
}
