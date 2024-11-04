package stardewValley.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

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
    public static boolean paint;


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

            this.sizeW = 3* canvas.getWidth() * 0.0325520833;
            this.sizeH = 6.5*canvas.getHeight() * 0.0578703704;
            position.setX(0);
            position.setY(canvas.getHeight() * 0.4016203704);
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

}
