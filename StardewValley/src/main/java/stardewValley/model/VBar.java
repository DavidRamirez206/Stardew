package stardewValley.model;

import javafx.scene.canvas.Canvas;

public class VBar {

    //Real atributes
    private Canvas canvas;
    public static VBar instance;

    //No borrar estos porcentajes (son para mantener las proporciones en el escenario para colisiones)
    //Pared adyacente a la segunda pared blanca
    private Position position1;

    //Pared adyacente izquierda al piso del centro
    private Position position2;

    //Pared adyacente derecha al piso del centro
    private Position position3;

    public VBar(Canvas canvas) {
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
        position1.setX(0.4622395833 * canvas.getWidth());
        position1.setY(0.4050925926 * canvas.getHeight());
    }

    public void drawB2() {

        position2.setX(0.4720052083 * canvas.getWidth());
        position2.setY(0.7060185185 * canvas.getHeight());
    }

    public void drawB3() {
        position3.setX(0.7682291667 * canvas.getWidth());
        position3.setY(0.7060185185 * canvas.getHeight());
    }

    public static VBar getInstance() {
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
