package stardewValley.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class House {

    //Graphic elements
    private Canvas canvas;
    private GraphicsContext gc;

    private Image house;


    private Position position;
    private double sizeH = 80;
    private double sizeW = 80;

    //Keys
    public static boolean paint;


    public House(Canvas canvas) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        this.position = new Position(100, 100);
        paint = false;
    }

    private Image loadImage(String basePath, int count) {
        return new Image(getClass().getResourceAsStream(basePath + ".png"), sizeW, sizeH, false, false);
    }

    private void loadHouse() {
        house = loadImage("/img/object/House", 6);
    }


    public void draw() {
        if (paint == false) {
            house = null;

            this.sizeW = canvas.getWidth() * 0.0325520833 * 4;
            this.sizeH = canvas.getHeight() * 0.0578703704 * 4;
            position.setX((canvas.getWidth() / 2) - (sizeW / 2));
            position.setY((canvas.getHeight() / 2) - (sizeH / 2));
            loadHouse();
            paint = true;
        }

        gc.drawImage(house, position.getX(), position.getY());
    }

    public Position getPosition() {
        return position;
    }

    public void setPaint(boolean b) {
        this.paint = b;
    }

}
