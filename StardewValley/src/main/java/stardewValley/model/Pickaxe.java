package stardewValley.model;

import javafx.scene.canvas.Canvas;

public class Pickaxe extends Obstacle {

    public Pickaxe(Canvas canvas, String basePath, double positionX, double positionY, double sW, double sH) {
        super(canvas, basePath, positionX, positionY, sW, sH);
    }

    @Override
    protected void otherLoad(){}

    @Override
    public void otherDraw(){}

    @Override
    public void otherDraw2(){}
}
