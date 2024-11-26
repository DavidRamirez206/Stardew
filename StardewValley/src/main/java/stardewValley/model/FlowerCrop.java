package stardewValley.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class FlowerCrop {

    private Image image;
    private long lastDirectionChange;

    private boolean lastGrowing;
    private int frame;

    private Canvas canvas;
    private GraphicsContext gc;
    private double positionX;
    private double positionY;

    private double height;
    private double width;
    private double sizeW;
    private double sizeH;

    private Position  position;

    private boolean paint;
    private boolean firstPaint;


    public FlowerCrop(Canvas canvas, double positionX, double positionY, double width, double height) {
        this.image = loadImg();
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        this.positionX = positionX;
        this.positionY = positionY;
        this.height = height;
        this.width = width;
        position = new Position(positionX, positionY);
        adjustSize();
    }

    private Image loadImg() {
        String imagePath = "/img/Others/plant/flower/florCrop" + (frame + 1) + ".png";
        return new Image(getClass().getResourceAsStream(imagePath), sizeW, sizeH, false, false);
    }


    public boolean isPaint() {
        return paint;
    }

    public void setPaint(boolean paint) {
        this.paint = paint;
    }

    public void draw() {

        if(!firstPaint){
            lastDirectionChange = System.currentTimeMillis();
            firstPaint = true;
        }

        if (!paint) {
            adjustSize();
            image = loadImg();
            paint = true;
        }

        update();

        gc.drawImage(image, position.getX(), position.getY());

        /*
        sizeW = canvas.getWidth() * width;
        sizeH = canvas.getHeight() * height;

        position.setX((canvas.getWidth() * positionX) - (sizeW / 2));
        position.setY(canvas.getHeight() * positionY);

         */
    }

    public void update() {
        if ((System.currentTimeMillis() - lastDirectionChange > 10000) && !lastGrowing) {
            frame++;
            if (frame >= 4) {
                lastGrowing = true;
                frame = 3;
            }
            image = loadImg();

            lastDirectionChange = System.currentTimeMillis();
        }
    }


    protected void adjustSize() {
        sizeW = canvas.getWidth() * width;
        sizeH = canvas.getHeight() * height;

        //position.setX((canvas.getWidth() * positionX) - (sizeW / 2));
        position.setX((canvas.getWidth() * positionX));
        position.setY(canvas.getHeight() * positionY);
    }

}
