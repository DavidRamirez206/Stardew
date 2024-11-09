package stardewValley.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Tree {

    //Graphic elements
    private Canvas canvas;
    private GraphicsContext gc;

    private Image half1;
    private Image half2;


    private Position position;
    private double sizeH = 40;
    private double sizeW = 80;

    private Position position2;
    private double sizeH2 = 40;
    private double sizeW2 = 80;

    //Keys
    private boolean paint;
    private boolean paint2;

    String basePath = "/img/object/tree/";


    public Tree(Canvas canvas, String basePath) {
        this.basePath += basePath;
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        this.position = new Position(100, 100);
        this.position2 = new Position(100, 100);
        paint = false;
        paint2 = false;
    }

    private Image loadImage(String path, double sizeW, double sizeH) {
        return new Image(getClass().getResourceAsStream(basePath + path + ".png"), sizeW, sizeH, false, false);
    }

    private void load1() {
        half1 = loadImage("/Tree1", this.sizeW, this.sizeH);
    }

    private void load2() {
        half2 = loadImage("/Tree2", this.sizeW2, this.sizeH2);
    }


    public void draw1() {

        if (paint == false) {
            reLoad(1);
            load1();
            paint = true;
        }

        gc.drawImage(half1, position.getX(), position.getY());
    }

    public void draw2() {
        if (paint2 == false) {
            reLoad(2);
            load2();
            paint2 = true;
        }

        gc.drawImage(half2, position2.getX(), position2.getY());
    }

    private void reLoad(int imageNumber) {
        if (imageNumber == 1) {
            sizeW = canvas.getWidth() * 0.0325520833 * 4;
            sizeH = canvas.getHeight() * 0.0578703704 * 2;
            position.setX((canvas.getWidth() / 2) - (sizeW / 2));
            position.setY(canvas.getHeight() * 0.5150462963);
            //position.setY((canvas.getHeight() / 2) - (sizeH / 2)); 0.3177083333
        } else if (imageNumber == 2) {
            sizeW2 = canvas.getWidth() * 0.0325520833 * 4;
            sizeH2 = canvas.getHeight() * 0.0578703704 * 2;
            position2.setX((canvas.getWidth() / 2) - (sizeW2 / 2));
            position2.setY(canvas.getHeight() * 0.3993055556);
            //position2.setY((canvas.getHeight() / 2) - (sizeH2 / 2)); 0.3377250463
        }
    }

    public Position getPosition() {
        return position;
    }

    public void setPaint(boolean b) {
        this.paint = b;
    }

    public void setPaint2(boolean b) {
        this.paint2 = b;
    }

}
