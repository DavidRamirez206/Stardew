package stardewValley.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class River extends CharacterMove {

    private ArrayList<Image> moving;

    public River(Canvas canvas) {
        super(canvas);
    }

    @Override
    protected void initArrayList() {
        this.moving = new ArrayList<>();
    }

    private void setIdles() {
        moving = loadImages("/img/object/Scene3/river/river", 2);
    }

    @Override
    public void draw() {
        if(paint == false){
            moving.clear();

            this.width = canvas.getWidth();
            this.height = canvas.getHeight();
            position.setY(0);
            position.setX(0);
            setIdles();
            paint = true;
        }

        gc.drawImage(moving.get(frame % moving.size()), position.getX(), position.getY());

        frame++;
    }

    @Override
    public void onMove(){}

    @Override
    protected void updatePosition2() {
    }

}
