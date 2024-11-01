package stardewValley.screens;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import stardewValley.control.Controller;
import stardewValley.model.Foxy;

public class ScreenA extends SceneBase {

    private Foxy foxy;

    public ScreenA(Canvas canvas) {
        super(canvas);
        this.foxy = new Foxy(super.canvas);
    }

    @Override
    public void paint(){
        super.gc.setFill(Color.BLACK);
        super.gc.fillRect(0, 0, super.canvas.getWidth(), super.canvas.getHeight());
        foxy.draw();

        System.out.println(foxy.getPosition().getX());

        if (foxy.getPosition().getX() < 0){
            foxy.getPosition().setX(0);
        }

    }

    @Override
    public void onKeyPressed(KeyEvent event){
        foxy.setOnKeyPressed(event);

        if (event.getCode() == KeyCode.SPACE){
            Controller.SCREEN = 1;
        }
    }

    @Override
    public void onKeyReleased(KeyEvent event){
        foxy.onKeyReleased(event);
    }
}
