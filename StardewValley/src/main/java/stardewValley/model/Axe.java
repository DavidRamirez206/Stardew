package stardewValley.model;

import javafx.scene.canvas.Canvas;

import java.util.Random;

public class Axe extends Obstacle {

    public Axe(Canvas canvas, String basePath, double positionX, double positionY, double sW, double sH) {
        super(canvas, basePath, positionX, positionY, sW, sH);
        this.durability = 100.0;
    }

    @Override
    protected void otherLoad(){}

    @Override
    public void otherDraw(){}

    @Override
    public void otherDraw2(){}

    @Override
    public boolean handleCutting(Position foxyPosition, double foxySizeW, double foxySizeH, String tool, boolean isFoxyCutting) {
        return false;
    }

    @Override
    public boolean isFoxyInside(Position foxyPosition, double foxySizeW, double foxySizeH) {
        return false;
    }

    public void repair(){
        double newDurability = new Random().nextDouble(20.0);
        if(!cut){
            if(durability + newDurability > 100){
                this.durability = 100;
            } else {
                this.durability += newDurability;
            }
        }
    }
}
