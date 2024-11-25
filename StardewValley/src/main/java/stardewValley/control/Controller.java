package stardewValley.control;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import stardewValley.model.Axe;
import stardewValley.model.Foxy;
import stardewValley.model.Pickaxe;
import stardewValley.screens.*;
import stardewValley.screens.ScreenB;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Label scoreLabel;
    @FXML
    private Label stoneLabel;
    @FXML
    private Label woodLabel;
    @FXML
    private Label foxyHealthLabel;
    @FXML
    private Label toolDurabilityLabel;
    @FXML
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private ArrayList<SceneBase> screens;
    private boolean isRunning;
    public static int SCREEN = 0;
    private Foxy foxy;
    private Pickaxe pickaxe;
    private Axe axe;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tools(canvas);
        applyFontAndColorL(scoreLabel, stoneLabel, woodLabel, foxyHealthLabel, toolDurabilityLabel);
        load();
        runningScenes();
    }

    private void  load(){
        this.graphicsContext = canvas.getGraphicsContext2D();
        this.foxy = new Foxy(this.canvas, axe, pickaxe);
        screens = new ArrayList<>(3);
        screens.add(new ScreenA(canvas, "scene1Background", foxy));
        screens.add(new ScreenB(canvas, "scene2Background", foxy));
        screens.add(new ScreenC(canvas, "scene3Background", foxy, axe, pickaxe));
    }

    public void runningScenes() {
        initActions();
        isRunning = true;

        new Thread(() -> {
            while (isRunning) {
                Platform.runLater(() -> {
                    screens.get(SCREEN).redraw();

                    String score = String.format("%.2f", foxy.getScore());
                    String foxyHealth = String.format("%.2f", foxy.getHealth());
                    String durability = String.format("%.2f", foxy.getToolDurability());


                    // ------------
                    scoreLabel.setText("Score: " + score);
                    stoneLabel.setText("Stone: " + foxy.getStones());
                    woodLabel.setText("Wood: " + foxy.getWood());
                    foxyHealthLabel.setText("Health: " + foxyHealth + "%");

                    toolDurabilityLabel.setText("Durability: " + durability + "%" +
                            "\n______________________________________" +
                            "\n\nTool equipped: " + foxy.toolEquipped());
                });

                try {
                    Thread.sleep(80);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public void setRunning(){
        isRunning = false;
    }

    public void initActions(){
        canvas.setOnKeyPressed(event -> {
            screens.get(SCREEN).onKeyPressed(event);
        });

        canvas.setOnKeyReleased(event -> {
            screens.get(SCREEN).onKeyReleased(event);
        });
    }

    private void applyFontAndColorL(Label label, Label label1, Label label2, Label label3, Label label4){
        applyFontAndColor(label, 15, Color.WHITE);
        applyFontAndColor(label1, 15, Color.WHITE);
        applyFontAndColor(label2, 15, Color.WHITE);
        applyFontAndColor(label3, 15, Color.WHITE);
        applyFontAndColor(label4, 15, Color.WHITE);
    }

    private void applyFontAndColor(Label label, double size, Color color) {
        Font pixelFont = Font.loadFont(getClass().getResourceAsStream("/fonts/PixelFont.ttf"), size);
        label.setFont(pixelFont);
        label.setTextFill(color);
    }

    private void tools(Canvas canvas){
        this.axe = new Axe(canvas, "/img/object/Scene3/tools/StoneAxe", 0.8984375, 0.03472222221, 0.03255208333, 0.05787037037);
        this.pickaxe = new Pickaxe(canvas, "/img/object/Scene3/tools/StonePickaxe", 0.21484375, 0.162037037, 0.03255208333, 0.05787037037);
    }

}
