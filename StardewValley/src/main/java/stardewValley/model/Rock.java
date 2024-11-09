package stardewValley.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Random;

public class Rock {

    //Graphic elements
    private Canvas canvas;
    private GraphicsContext gc;

    private Image image;


    private Position position;
    private double sizeH;
    private double sizeW;
    private int type;
    private double positionX;
    private double positionY;

    private boolean cut;

    //Keys
    private boolean paint;


    /*
        Type:
        * 1. Largest
        * 2. Large
        * 3. Medium
        * 4. Small
        * 5. Smallest
    */
    public Rock(Canvas canvas, double positionX, double positionY, int type) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        this.positionX = positionX;
        this.positionY = positionY;
        if(type != 0){
            this.type=type;
        } else {
            this.type = new Random().nextInt(5) + 1;
        }
        this.position = new Position(100, 100);
        paint = false;
        cut = false;
    }


    private void size(){

        switch (type){
            case 1 -> {
                updateSize(0.02604166667, 0.0462962963);
            }
            case 2 -> {
                updateSize(0.03255208333, 0.05787037037);
            }
            case 3 -> {
                updateSize(0.0390625, 0.06944444444);
            }
            case 4 -> {
                updateSize(0.04557291667, 0.08101851852);

            }
            case 5 -> {
                updateSize(0.05208333333, 0.09259259259);
            }
        }
    }

    private void updateSize(double sW, double sH){
        sizeW = canvas.getWidth() * sW;
        sizeH = canvas.getHeight() * sH;
        position.setX(canvas.getWidth() * positionX);
        position.setY(canvas.getHeight() * positionY);
    }

    private Image loadImage(String basePath, double sizeW, double sizeH) {
        return new Image(getClass().getResourceAsStream(basePath + ".png"), sizeW, sizeH, false, false);
    }

    private void load1() {
        switch (type) {
            case 1 -> {
                image = loadImage("/img/object/Scene3/rock/smallestRock", this.sizeW, this.sizeH);
            }
            case 2 -> {
                image = loadImage("/img/object/Scene3/rock/smallRock", this.sizeW, this.sizeH);
            }
            case 3 -> {
                image = loadImage("/img/object/Scene3/rock/mediumRock", this.sizeW, this.sizeH);
            }
            case 4 -> {
                image = loadImage("/img/object/Scene3/rock/largeRock", this.sizeW, this.sizeH);
            }
            case 5 -> {
                image = loadImage("/img/object/Scene3/rock/largestRock", this.sizeW, this.sizeH);
            }
        }

    }


    public void draw() {
        if(cut == false){
            if (paint == false) {
                load1();
                size();
                paint = true;
            }

            gc.drawImage(image, position.getX(), position.getY());
        }


    }

    public Position getPosition() {
        return position;
    }

    public void setPaint(boolean b) {
        this.paint = b;
    }

}
