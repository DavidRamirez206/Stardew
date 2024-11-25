package stardewValley.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bear extends CharacterMove {
    private Random random;
    private List<Image> runR, runL;
    private final double WIDTH_PROPORTION = 0.1302083333;
    private final double HEIGHT_PROPORTION = 0.2314814815;
    private long lastDirectionChange;
    private boolean isDead;
    private int kicked;

    public Bear(Canvas canvas) {
        super(canvas);
        esc = 2;
        this.random = new Random();
        lastDirectionChange = System.currentTimeMillis();
        initArrayList();
    }

    @Override
    protected void initArrayList() {
        runR = new ArrayList<>();
        runL = new ArrayList<>();
    }

    @Override
    public void draw() {
        if (!paint) {
            runR.clear();
            runL.clear();
            this.width = canvas.getWidth() * WIDTH_PROPORTION;
            this.height = canvas.getHeight() * HEIGHT_PROPORTION;
            runR = loadImages("/img/Characters/Animals/Bear/run/R/Bear-run-", 5);
            runL = loadImages("/img/Characters/Animals/Bear/run/L/Bear-run-", 5);
            paint = true;
        }

        // Mueve al oso
        update();
        onMove();
        List<Image> imgs = lookAtRight ? runR : runL;

        gc.drawImage(imgs.get(frame % imgs.size()), position.getX(), position.getY());
        frame++;


        gc.setStroke(Color.RED);
        gc.strokeRect(position.getX(), position.getY(), width, height);


    }

    @Override
    public void onMove() {
        if (isChangePosition() == true) {
            updatePosition();
        }

        newX = position.getX();
        newY = position.getY();


        double my = 0.00289351852 * canvas.getHeight();
        double mx = 0.00162760417 * canvas.getWidth();

        if (right) newX += mx;
        if (left) newX -= mx;
        if (up) newY -= my;
        if (down) newY += my;

        if (newX < 0) newX = 0;
        if (newX + width > canvasWidth) newX = canvasWidth - width;
        if (newY + (height / 1.7) < 0) newY = 0 - height / 1.7;
        if (newY + height > canvasHeight) newY = canvasHeight - height;

        if (esc == 2) {
            collisionsIn2();
        }

        position.setX(newX);
        position.setY(newY);
    }


    /*
    @Override
    public void onMove() {
        if(isChangePosition() == true){
            updatePosition();
        }

        newX = position.getX();
        newY = position.getY();
        double my = 0.005787037037 * canvas.getHeight(); // Movimiento en y
        double mx = 0.003255208333 * canvas.getWidth(); // Movimiento en x

        if (right) newX += mx;
        if (left) newX -= mx;
        if (up) newY -= my;
        if (down) newY += my;

        if (newX < 0) {
            newX = 0;
        } else if (newX + width > canvasWidth) {
            newX = canvasWidth - width;
        }

        if (newY + (height / 1.7) < 0) {
            newY = 0 - height / 1.7;
        } else if (newY + height > canvasHeight) {
            newY = canvasHeight - height;
        }

        position.setX(newX);
        position.setY(newY);
    }

     */

    public void update() {

        if (System.currentTimeMillis() - lastDirectionChange > 1500) {
            int direction = random.nextInt(8) + 1;
            moveBear(direction);
            lastDirectionChange = System.currentTimeMillis();
        }
    }

    @Override
    protected void updatePosition2() {
        if(isFirstPosition){
            /*
            position.setX(canvas.getWidth() * 0.753125);
            position.setY(canvas.getHeight() * 0.3136574074);

            position.setX(canvas.getWidth() / 4);
            position.setY(canvas.getHeight() / 4);
            */

            position.setX(canvas.getWidth() / 8);
            position.setY(canvas.getHeight() / 3.5);
            System.out.println("Posición oso: (" + position.getX() + ", " + position.getY() + ")");
            isFirstPosition = false;
        } else if (canvasWidth != 0 && canvasHeight != 0) {
            // Lo siguiente es para ajustar la posición basándose en las nuevas dimensiones del canvas
            position.setX(proportionX * canvasWidth);
            position.setY(proportionY * canvasHeight);
        } else {
            // Esto es por si acaso lo anterior falla
            position.setX(0);
            position.setY(0);
        }

        changePosition = false;
    }

    private void moveBear(int direction) {

        right = false;
        left = false;
        up = false;
        down = false;

        switch (direction) {

            case 1, 5 -> { right = true; lookAtRight = true; }
            case 2, 6 -> { left = true; lookAtRight = false; }
            case 3, 7 -> up = true;
            case 4, 8 -> down = true;

            //case 1, 2, 5, 3, 4, 6, 7, 8 -> { right = true; lookAtRight = true; }
            //case 1, 2, 5, 3, 4, 6, 7, 8 -> { up = true; }
            //case 1, 2, 5, 3, 4, 6, 7, 8 -> { down = true; }

        }
    }

    public boolean isDead() {
        return isDead;
    }

    public void collisionsIn2(){

        fromTo(0, 0.3819444446 - (HEIGHT_PROPORTION / 2), 0.4557291667, 0.3819444446 - (HEIGHT_PROPORTION / 2)); //Horizontal

        //Segunda pared superior (la pared blanca de la derecha)
        fromTo(0.4557291667 - (HEIGHT_PROPORTION / 2), 0.1279578183, 1, 0.1279578183 - (HEIGHT_PROPORTION / 2)); //Horizontal

        //Pared adyacente a la segunda pared blanca
        fromTo(0.4557291667, 0.1279578183, 0.4557291667, 0.3819444446); //Vertical

        //Primer piso (el piso debajo de la primera pared blanca)
        fromTo(0, 0.7002314815 - (HEIGHT_PROPORTION / 1.4), 0.458984375, 0.7002314815 - (HEIGHT_PROPORTION / 1.4)); //Horizontal


        //Tercer piso (el piso a la parte derecha del tunel del escenario 2)
        fromTo(0.7811197917, 0.7071759259 - (HEIGHT_PROPORTION / 1.4), 1, 0.7071759259 - (HEIGHT_PROPORTION / 1.4)); //Horizontal

        //Pared iquierda del tunel para salir al escenario 1
        fromTo(0.458984375, 0.7002314815, 0.458984375, 0.8680555556); //Vertical

        //Pared derecha del tunel para salir al escenario 1
        fromTo(0.7811197917, 0.7071759259,0.7811197917, 0.8680555556); //Vertical

        ///////////////////////////
        fromTo(0.234375, 0.6944444445, 0.2473958333, 0.601851852);
        fromTo(0.2473958333, 0.601851852, 0.3059895833, 0.601851852);
        fromTo(0.3059895833, 0.601851852, 0.3580729167, 0.613425926);
        fromTo(0.3580729167, 0.613425926, 0.390625, 0.6365740742);
        fromTo(0.390625, 0.6365740742, 0.390625, 0.6979166667);

        //Table 1
        //Diagonal inferior L
        fromTo(0.5794270834, 0.5092592591, 0.60546875, 0.5902777776);
        //Horizontal inferior L
        fromTo(0.60546875, 0.5902777776 - (HEIGHT_PROPORTION / 1.4), 0.6575520834, 0.5902777776 - (HEIGHT_PROPORTION / 1.4));
        //Diagonal inferior R
        fromTo(0.6575520834, 0.5902777776, 0.68359375, 0.5439814813);
        //Lateral R
        fromTo(0.68359375, 0.5439814813, 0.68359375, 0.3009259258);
        //Horizontal superior
        fromTo(0.68359375 - (HEIGHT_PROPORTION / 2), 0.3009259258, 0.5794270834, 0.3009259258 - (HEIGHT_PROPORTION / 2));
        //Lateral L
        fromTo(0.5794270834, 0.3009259258, 0.5794270834, 0.5092592591);

        //Tables 1 and 2
        //Horizontal inferior
        fromTo(0.8333333334 - (HEIGHT_PROPORTION / 1.4), 0.659722222, 0.9440104167, 0.659722222 - (HEIGHT_PROPORTION / 1.4));
        //Horizontal superior
        fromTo(0.8333333334- (HEIGHT_PROPORTION / 2), 0.3587962962, 0.9440104167, 0.3587962962 - (HEIGHT_PROPORTION / 2));
        //Lateral L
        fromTo(0.8333333334, 0.659722222, 0.8333333334, 0.3587962962);
        //Lateral R
        fromTo(0.9440104167, 0.659722222, 0.9440104167, 0.3587962962);

    }

    /*
    public boolean isFoxyInside(Position foxyPosition, double foxySizeW, double foxySizeH) {
        return foxyPosition.getX() + foxySizeW > position.getX() && foxyPosition.getX() < position.getX() +
                WIDTH_PROPORTION && foxyPosition.getY() + foxySizeH > position.getY() + (HEIGHT_PROPORTION) && foxyPosition.getY() < position.getY() + (2 * HEIGHT_PROPORTION);
    }


     */

    public boolean isFoxyInside(Position foxyPosition, double foxySizeW, double foxySizeH) {

        /*
        double bearLeft = position.getX() - foxySizeW;
        double bearRight = position.getX() + width + foxySizeW;
        double bearTop = position.getY() + (height / 2) - foxySizeH;
        double bearBottom = position.getY() + height + foxySizeH;

         */

        double bearLeft = position.getX() + (width / 4);
        double bearRight = position.getX() + width - (width / 4);
        double bearTop = position.getY() + (height / 2);
        double bearBottom = position.getY() + height;

        return foxyPosition.getX() + foxySizeW > bearLeft &&
                foxyPosition.getX() < bearRight &&
                foxyPosition.getY() + foxySizeH > bearTop &&
                foxyPosition.getY() < bearBottom;
    }


    public boolean handleCutting(Position foxyPosition, double foxySizeW, double foxySizeH, String tool, boolean isFoxyCutting) {
        if (isFoxyInside(foxyPosition, foxySizeW, foxySizeH)) {
            System.out.println("El zorro está dentro");
            if(!isDead()){
                if(isFoxyCutting && (tool.equalsIgnoreCase("axe" ) || tool.equalsIgnoreCase("pickaxe" ))){
                    return true;
                }
            }
        }

        return false;
    }

    public void setKicked(int kicked) {
        this.kicked += kicked;
    }

    public int getKicked() {
        return kicked;
    }

    public void setIsDead(boolean isDead) {
        this.isDead = isDead;
    }
}
