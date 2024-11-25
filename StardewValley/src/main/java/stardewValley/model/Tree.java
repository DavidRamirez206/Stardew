package stardewValley.model;

import javafx.scene.canvas.Canvas;

import java.util.HashMap;
import java.util.Map;

public class Tree extends Obstacle {

    /*
    public Tree(Canvas canvas, String basePath, String basePath2, double positionX, double positionY, double sizeW, double sizeH) {
        super(canvas, basePath, basePath2, positionX, positionY, sizeW, sizeH);
    }

     */

    public Tree(Canvas canvas, double positionX, double positionY, int type, double width, double height) {
        super(canvas, positionX, positionY, type, 7
                , width, height); //Aquí pasamos el intervalo para el ramdon
    }

    @Override
    protected void otherLoad() {
        switch (type) {
            case 1 -> {
                half1 = loadImage("/img/object/tree/1/Tree1", this.sizeW, this.sizeH);
                half2 = loadImage("/img/object/tree/1/Tree2", this.sizeW, this.sizeH);
            }
            case 2 -> {
                half1 = loadImage("/img/object/tree/2/Tree1", this.sizeW, this.sizeH);
                half2 = loadImage("/img/object/tree/2/Tree2", this.sizeW, this.sizeH);
            }
            case 3 -> {
                half1 = loadImage("/img/object/tree/3/Tree1", this.sizeW, this.sizeH);
                half2 = loadImage("/img/object/tree/3/Tree2", this.sizeW, this.sizeH);
            }
            case 4 -> {
                half1 = loadImage("/img/object/tree/4/Tree1", this.sizeW, this.sizeH);
                half2 = loadImage("/img/object/tree/4/Tree2", this.sizeW, this.sizeH);
            }
            case 5 -> {
                half1 = loadImage("/img/object/tree/5/Tree1", this.sizeW, this.sizeH);
                half2 = loadImage("/img/object/tree/5/Tree2", this.sizeW, this.sizeH);
            }
            case 6 -> {
                half1 = loadImage("/img/object/tree/6/Tree1", this.sizeW, this.sizeH);
                half2 = loadImage("/img/object/tree/6/Tree2", this.sizeW, this.sizeH);
            }
            case 7 -> {
                half1 = loadImage("/img/object/tree/7/Tree1", this.sizeW, this.sizeH);
                half2 = loadImage("/img/object/tree/7/Tree2", this.sizeW, this.sizeH);
            }
        }

    }

    @Override
    public void otherDraw() {
        if(cut == false){
            if (paint == false) {
                adjustSize();
                otherLoad();
                paint = true;
            }

            gc.drawImage(half1, position.getX(), position.getY());
        }
    }

    @Override
    public void otherDraw2() {
        if(cut == false){
            if (paint == false) {
                adjustSize();
                otherLoad();
                paint = true;
            }

            gc.drawImage(half2, position2.getX(), position2.getY());
        }
    }

    @Override
    public boolean isFoxyInside(Position foxyPosition, double foxySizeW, double foxySizeH) {
        return foxyPosition.getX() + foxySizeW > position.getX() && foxyPosition.getX() < position.getX() +
                sizeW && foxyPosition.getY() + foxySizeH > position.getY() + (sizeH) && foxyPosition.getY() < position.getY() + (2 * sizeH);
    }

    @Override
    public boolean handleCutting(Position foxyPosition, double foxySizeW, double foxySizeH, String tool, boolean isFoxyCutting) {
        if (isFoxyInside(foxyPosition, foxySizeW, foxySizeH)) {
                if(!cut){
                    if(isFoxyCutting && tool.equalsIgnoreCase("axe")){
                        return true;
                    }
                }
        }

        return false;
    }
}