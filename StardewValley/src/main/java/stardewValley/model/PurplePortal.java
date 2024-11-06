package stardewValley.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class PurplePortal {

    //Graphic elements
    private Canvas canvas;
    private GraphicsContext gc;

    private ArrayList<Image> idles;


    private Position position;
    private double sizeH = 80;
    private double sizeW = 80;
    private int frame;

    //Keys
    private boolean paint;
    private double yPosition = 0.4016203704;
    private double newSizeH = 6.5;
    private double newSizeW = 3;


    public PurplePortal(Canvas canvas) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        this.idles = new ArrayList<>();
        this.frame = 0;
        this.position = new Position(100, 100);
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
        idles = loadImages("/img/object/portal/purplePortal-", 6);
    }


    public void draw() {
        if(paint == false){
            //No modificar los siguientes productos. Son para mantener proporciones
            idles.clear();

            this.sizeW = newSizeW * canvas.getWidth() * 0.0325520833;
            this.sizeH = newSizeH *canvas.getHeight() * 0.0578703704;
            position.setX(0);
            position.setY(canvas.getHeight() * yPosition);
            setIdles();
            paint = true;
        }

        gc.drawImage(idles.get(frame % idles.size()), position.getX(), position.getY());

        frame++;
    }

    public Position getPosition(){
        return position;
    }

    public void setPaint(boolean b){
        this.paint = b;
    }

    public void setYPosition(double yPosition) {
        this.yPosition = yPosition;
    }

    public void setNewSizeH(double newSizeH) {
        this.newSizeH = newSizeH;
    }

    public void setNewSizeW(double newSizeW) {
        this.newSizeW = newSizeW;
    }
}
