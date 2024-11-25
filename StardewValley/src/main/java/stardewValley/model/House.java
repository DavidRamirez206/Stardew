package stardewValley.model;

import javafx.scene.canvas.Canvas;

public class House extends Obstacle {

    public House(Canvas canvas, String basePath, String basePath2, double positionX, double positionY, double sizeW, double sizeH) {
        super(canvas, basePath, basePath2, positionX, positionY, sizeW, sizeH);
    }

    @Override
    protected void otherLoad(){}

    @Override
    public void otherDraw(){}

    @Override
    public void otherDraw2(){}

    @Override
    public boolean handleCutting(Position foxyPosition, double foxySizeW, double foxySizeH, String tool, boolean isFoxyCutting){
        return false;
    }

    public boolean isFoxyInside(Position foxyPosition, double foxySizeW, double foxySizeH) {
        return false;
    }
}
