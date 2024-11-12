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
import stardewValley.model.Foxy;
import stardewValley.screens.*;
import stardewValley.screens.ScreenB;

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
    private Button backpackButton;
    @FXML
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private ArrayList<SceneBase> screens;
    private boolean isRunning;
    public static int SCREEN = 0;
    private Foxy foxy;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        applyFontAndColorL(scoreLabel, stoneLabel, woodLabel, foxyHealthLabel, toolDurabilityLabel);
        setBackpackButtonImage();
        runningScenes();
    }

    private void setBackpackButtonImage() {
        Image backpackImage = new Image(getClass().getResourceAsStream("/img/object/backpack.png"));
        ImageView imageView = new ImageView(backpackImage);
        imageView.setFitWidth(80);
        imageView.setFitHeight(80);
        backpackButton.setGraphic(imageView);
        backpackButton.setText("");
    }

    public void runningScenes() {
        this.graphicsContext = canvas.getGraphicsContext2D();
        this.foxy = new Foxy(this.canvas);
        screens = new ArrayList<>(3);
        screens.add(new ScreenA(canvas, "scene1Background", foxy));
        screens.add(new ScreenB(canvas, "scene2Background", foxy));
        screens.add(new ScreenC(canvas, "scene3Background", foxy));

        initActions();
        isRunning = true;

        new Thread(() -> {
            while (isRunning) {
                Platform.runLater(() -> {
                    screens.get(SCREEN).redraw();

                    // ------------
                    scoreLabel.setText("Score: " + foxy.getScore());
                    stoneLabel.setText("Stones: " + foxy.getStones());
                    woodLabel.setText("Wood: " + foxy.getWood());
                    foxyHealthLabel.setText("Health: " + foxy.getHealth() + "%");
                    toolDurabilityLabel.setText("Durability: " + foxy.getToolDurability());
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

    @FXML
    private void onBackpackButtonClick() {
        System.out.println("------------------");
    }

}
