package stardewValley.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class River {

    //Graphic elements
    private Canvas canvas;
    private GraphicsContext gc;

    private ArrayList<Image> moving;


    private Position position;
    private double sizeH;
    private double sizeW;
    private int frame;

    //Keys
    public boolean paint;


    public River(Canvas canvas) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        this.moving = new ArrayList<>();
        this.frame = 0;
        sizeH = canvas.getHeight();
        sizeW = canvas.getWidth();
        this.position = new Position(0, 0);
        paint = false;
    }

    private ArrayList<Image> loadImages(String basePath, int count) {
        ArrayList<Image> images = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            images.add(new Image(getClass().getResourceAsStream(basePath + i + ".png"), sizeW, sizeH, false, false));
        }
        return images;
    }

    private void setIdles() {
        moving = loadImages("/img/object/Scene3/river/river", 2);
    }

    public void draw() {
        if(paint == false){
            //No modificar los siguientes productos. Son para mantener proporciones
            moving.clear();

            this.sizeW = canvas.getWidth();
            this.sizeH = canvas.getHeight();
            setIdles();
            paint = true;
        }

        gc.drawImage(moving.get(frame % moving.size()), position.getX(), position.getY());

        frame++;
    }

    public Position getPosition(){
        return position;
    }

    public void setPaint(boolean b){
        this.paint = b;
    }

}
