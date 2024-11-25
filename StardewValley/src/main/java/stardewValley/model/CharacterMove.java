package stardewValley.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public abstract class CharacterMove {
    protected Canvas canvas;
    protected GraphicsContext gc;

    //
    protected double health;
    protected double newX;
    protected double newY;
    protected double canvasWidth;
    protected double canvasHeight;
    protected double proportionX;
    protected double proportionY;
    protected int frame;
    protected boolean isFirstPosition;


    protected double height;
    protected double width;

    protected boolean lookAtRight;

    protected Position  position;


    protected int esc;
    protected boolean paint;
    protected boolean changePosition;

    //Keys and boolean
    protected boolean right;
    protected boolean left;
    protected boolean up;
    protected boolean down;

    public CharacterMove(Canvas canvas) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();
        this.position = new Position(600, 400);
        initArrayList();
    }


    public void fromTo(double x1, double y1, double x2, double y2) {
        x1 *= canvasWidth;
        y1 *= canvasHeight;
        x2 *= canvasWidth;
        y2 *= canvasHeight;

        if (intersectsLine(position.getX(), position.getY(), newX, newY, x1, y1, x2, y2)) {
            newX = position.getX();
            newY = position.getY();
            //System.out.println("intersectsLine at (" + newX + ", " + newY + ")");
        }
    }


    private boolean intersectsLine(double x0, double y0, double x1, double y1, double x2, double y2, double x3, double y3) {
        double s1_x, s1_y, s2_x, s2_y;
        s1_x = x1 - x0;
        s1_y = y1 - y0;
        s2_x = x3 - x2;
        s2_y = y3 - y2;

        double s, t;
        double denominator = (-s2_x * s1_y + s1_x * s2_y);
        s = (-s1_y * (x0 - x2) + s1_x * (y0 - y2)) / denominator;
        t = ( s2_x * (y0 - y2) - s2_y * (x0 - x2)) / denominator;

        boolean result = s >= 0 && s <= 1 && t >= 0 && t <= 1;
        if(result) {
            //System.out.println("intersectsLine");
        }
        return result;
    }

    protected ArrayList<Image> loadImages(String basePath, int count) {
        ArrayList<Image> images = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            images.add(new Image(getClass().getResourceAsStream(basePath + i + ".png"), width, height, false, false));
        }
        return images;
    }

    //Este metodo es para el movimiento en cualquier escenario
    protected void anyScene(){
        newX = position.getX();
        newY = position.getY();

        double my = 0.01157407407 * canvasHeight; //movimiento en y
        double mx = 0.006510416667 * canvasWidth; //movimiento en x

        moving(my, mx);
    }

    protected void moving(double my, double mx){
        if (up) {
            newY -= my;
        }
        if (down) {
            newY += my;
        }
        if (right) {
            newX += mx;
        }
        if (left) {
            newX -= mx;
        }

        if (newX < 0) {
            newX = 0;
        } else if (newX + width > canvasWidth) {
            newX = canvasWidth - width;
        }

        if (newY < 0) {
            newY = 0;
        } else if (newY + height > canvasHeight) {
            newY = canvasHeight - height;
        }
    }

    //Este metodo es para cuando el canva cambie sus dimensiones (para que foxy no se mueva de posición)
    protected void updatePosition() {
        //Esto es para cuando el canva se inicialice por primera vez (hablo de las variables como canvasWidth y height)
        if (canvasWidth == 0 || canvasHeight == 0) {
            canvasWidth = canvas.getWidth();
            canvasHeight = canvas.getHeight();
        }

        proportionX = position.getX() / canvasWidth;
        proportionY = position.getY() / canvasHeight;

        // Con esto se actualizan las variables
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        updatePosition2();
    }

    protected abstract void updatePosition2();

    public void setChangePosition(boolean changePosition){
        this.changePosition = changePosition;
    }

    public boolean isChangePosition(){
        return changePosition;
    }

    public Position getPosition(){
        return position;
    }

    public void setEsc(int sc){
        esc = sc;
    }

    public void setPaint(boolean paint  ){
        this.paint = paint;
    }

    public double getHeight(){
        return height;
    }

    public double getWidth(){
        return width;
    }

    protected abstract void initArrayList();

    public abstract void draw();

    public abstract void onMove();

    public void setFirstPosition(boolean isFirstPosition){
        this.isFirstPosition = isFirstPosition;
    }
    public void generalCollisionsInScene2(){
        /*
         * //Horizontal ("Y" does not change in coordinates, except if it starts or ends at some end of the width)
         * //Vertical ("X" does not change in coordinates)
         */
        //Primera pared superior (la pared blanca de la izquierda)
        fromTo(0, 0.3819444446, 0.4557291667, 0.3819444446); //Horizontal

        //Segunda pared superior (la pared blanca de la derecha)
        fromTo(0.4557291667, 0.1279578183, 1, 0.1279578183); //Horizontal

        //Pared adyacente a la segunda pared blanca
        fromTo(0.4557291667, 0.1279578183, 0.4557291667, 0.3819444446); //Vertical

        //Primer piso (el piso debajo de la primera pared blanca)
        fromTo(0, 0.7002314815, 0.458984375, 0.7002314815); //Horizontal

        //Tercer piso (el piso a la parte derecha del tunel del escenario 2)
        fromTo(0.7811197917, 0.7071759259, 1, 0.7071759259); //Horizontal

        //Pared iquierda del tunel para salir al escenario 1
        fromTo(0.458984375, 0.7002314815, 0.458984375, 0.8680555556); //Vertical

        //Pared derecha del tunel para salir al escenario 1
        fromTo(0.7811197917, 0.7071759259,0.7811197917, 0.8680555556); //Vertical

        collisions2();
    }

    public void collisions2(){

        fromTo(0.234375, 0.6944444445, 0.2473958333, 0.601851852);
        fromTo(0.2473958333, 0.601851852, 0.3059895833, 0.601851852);
        fromTo(0.3059895833, 0.601851852, 0.3580729167, 0.613425926);
        fromTo(0.3580729167, 0.613425926, 0.390625, 0.6365740742);
        fromTo(0.390625, 0.6365740742, 0.390625, 0.6979166667);

        //Table 1
        //Diagonal inferior L
        fromTo(0.5794270834, 0.5092592591, 0.60546875, 0.5902777776);
        //Horizontal inferior L
        fromTo(0.60546875, 0.5902777776, 0.6575520834, 0.5902777776);
        //Diagonal inferior R
        fromTo(0.6575520834, 0.5902777776, 0.68359375, 0.5439814813);
        //Lateral R
        fromTo(0.68359375, 0.5439814813, 0.68359375, 0.3009259258);
        //Horizontal superior
        fromTo(0.68359375, 0.3009259258, 0.5794270834, 0.3009259258);
        //Lateral L
        fromTo(0.5794270834, 0.3009259258, 0.5794270834, 0.5092592591);

        //Tables 1 and 2
        //Horizontal inferior
        fromTo(0.8333333334, 0.659722222, 0.9440104167, 0.659722222);
        //Horizontal superior
        fromTo(0.8333333334, 0.3587962962, 0.9440104167, 0.3587962962);
        //Lateral L
        fromTo(0.8333333334, 0.659722222, 0.8333333334, 0.3587962962);
        //Lateral R
        fromTo(0.9440104167, 0.659722222, 0.9440104167, 0.3587962962);
    }
}
