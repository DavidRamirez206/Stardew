package stardewValley.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class HBar {

    //Real atributes
    private Canvas canvas;
    public static HBar instance;

    //No borrar estos porcentajes (son para mantener las proporciones en el escenario para colisiones)
    //Pared adyacente a la segunda pared blanca
    private Position position1;

    //Pared adyacente izquierda al piso del centro
    private Position position2;

    //Pared adyacente derecha al piso del centro
    private Position position3;

    public HBar(Canvas canvas) {
        this.canvas = canvas;
        this.position1 = new Position(0, 0);
        this.position2 = new Position(0, 0);
        this.position3 = new Position(0, 0);
    }

    //@Override
    public void draw() {
        instance = this;
        drawB1();
        drawB2();
        drawB3();
    }

    public void drawB1(){
        position1.setX(0.44921875 * canvas.getWidth());
        position1.setY(0.4050925926 * canvas.getHeight());
    }

    public void drawB2() {

        position2.setX(0.4557291667 * canvas.getWidth());
        position2.setY(0.162037037 * canvas.getHeight());
    }

    public void drawB3() {
        position3.setX(0.7805989583 * canvas.getWidth());
        position3.setY(0.6944444444 * canvas.getHeight());
    }

    public static HBar getInstance() {
        return instance;
    }

    //VBar
    public Position getPosition1() {
        return position1;
    }

    //VBar2
    public Position getPosition2() {
        return position2;
    }

    //VBar3
    public Position getPosition3() {
        return position3;
    }
}
