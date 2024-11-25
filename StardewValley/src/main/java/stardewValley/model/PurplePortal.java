package stardewValley.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class PurplePortal extends CharacterMove {

    //Graphic elements

    private ArrayList<Image> idles;
    private double newSizeW, newSizeH, x, y;


    public PurplePortal(Canvas canvas, double x, double y, double newSizeW, double newSizeH) {
        super(canvas);
        this.newSizeW = newSizeW;
        this.newSizeH = newSizeH;
        this.x = x;
        this.y = y;
    }

    @Override
    protected void initArrayList() {
        this.idles = new ArrayList<>();
    }

    private void setIdles() {
        idles = loadImages("/img/object/portal/purplePortal-", 6);
    }


    @Override
    public void draw() {
        if (paint == false) {
            //No modificar los siguientes productos. Son para mantener proporciones
            idles.clear();

            this.width = newSizeW * canvas.getWidth() * 0.0325520833;
            this.height = newSizeH * canvas.getHeight() * 0.0578703704;
            position.setX(x);
            position.setY(canvas.getHeight() * y);
            setIdles();
            paint = true;
        }

        gc.drawImage(idles.get(frame % idles.size()), position.getX(), position.getY());

        frame++;
    }

    @Override
    public void onMove() {

    }

    @Override
    protected void updatePosition2() {

    }

}
