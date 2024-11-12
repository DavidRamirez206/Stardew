package stardewValley.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Random;

public class Rock extends Obstacle {
    /*
        Type:
        * 1. Largest
        * 2. Large
        * 3. Medium
        * 4. Small
        * 5. Smallest
    */
    public Rock(Canvas canvas, double positionX, double positionY, int type, double width, double height) {
        super(canvas, positionX, positionY, type, 5, width, height); //Aquí pasamos el intervalo para el ramdon
    }

    @Override
    protected void otherLoad() {
        switch (type) {
            case 1 -> {
                half1 = loadImage("/img/object/Scene3/rock/smallestRock", this.sizeW, this.sizeH);
            }
            case 2 -> {
                half1 = loadImage("/img/object/Scene3/rock/smallRock", this.sizeW, this.sizeH);
            }
            case 3 -> {
                half1 = loadImage("/img/object/Scene3/rock/mediumRock", this.sizeW, this.sizeH);
            }
            case 4 -> {
                half1 = loadImage("/img/object/Scene3/rock/largeRock", this.sizeW, this.sizeH);
            }
            case 5 -> {
                half1 = loadImage("/img/object/Scene3/rock/largestRock", this.sizeW, this.sizeH);
            }
        }

    }

    @Override
    public void otherDraw() {
        if(cut == false){
            if (paint == false) {
                adjustSize2();
                otherLoad();
                paint = true;
            }

            gc.drawImage(half1, position.getX(), position.getY());
        }
    }

    @Override
    public void otherDraw2(){}

}
