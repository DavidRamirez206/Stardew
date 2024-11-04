package stardewValley.screens;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import stardewValley.control.Controller;
import stardewValley.ui.StardewValley;

public class Instructions {

    @FXML
    private Label pressEnterLabel;

    @FXML
    private StackPane rootVBox;

    @FXML
    private Label instructionLabel;

    @FXML
    private Button skipButton;

    @FXML
    private ImageView skipButtonImage;

    private int instructionIndex = 0;

    @FXML
    private ImageView backgroundImage; // Background

    private String[] instructions = {

            //Do not remove spaces in messages.

            //THEY ARE TO MAKE THE TEXT BETTER FOR VISIBILITY

            "Welcome to Stardew Valley.",
            "Use the \"UP\" key to \n\n       go up",
            "Use the \"DOWN\" key to \n\n      go down",
            "Use the \"LEFT\" key to \n\n      go left",
            "Use the \"RIGHT\" key to \n\n  go to the right",
            "Use the \"SPACE\" key to attack",
            "Use the \"ENTER\" key to \n\n       interact",
            "...",
            "Explore and have fun!",
    };

    @FXML
    public void initialize() {

        //Image backgroundImage = new Image("file:src/main/resources/img/background.png", true); --> This does not allow the resource to load properly
        Image image = new Image(getClass().getResourceAsStream("/img/background/instructionsBackground1.png"));

        backgroundImage = new ImageView(image);

        rootVBox.getChildren().add(0, backgroundImage);

        Image skipImage = new Image(getClass().getResourceAsStream("/img/Others/skip_icon.png"));
        skipButtonImage.setImage(skipImage);

        applyFontAndColor(instructionLabel, 40, Color.WHITE);
        instructionLabel.setTranslateY(-30);

        transition(pressEnterLabel, 15, Color.WHITE);
        pressEnterLabel.setTranslateY(-80);

        rootVBox.requestFocus();

        //This is to maintain the proportions of the background
        rootVBox.widthProperty().addListener((obs, oldVal, newVal) -> {
            backgroundImage.setFitWidth(newVal.doubleValue());
        });
        rootVBox.heightProperty().addListener((obs, oldVal, newVal) -> {
            backgroundImage.setFitHeight(newVal.doubleValue());
        });

        rootVBox.setOnKeyPressed(this::handleKeyPress);

        skipButton.setOnAction(event -> StardewValley.getInstance().changeScene(3));

        Platform.runLater(() -> rootVBox.requestFocus());
    }

    private void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case ENTER:

                if (instructionIndex < instructions.length - 1) {
                    instructionIndex++;
                    instructionLabel.setText(instructions[instructionIndex]);
                } else {
                    StardewValley.getInstance().changeScene(3);
                }
                break;

            case SPACE:

                if (instructionIndex > 0) {
                    instructionIndex--;
                    instructionLabel.setText(instructions[instructionIndex]);
                }
                break;
            default:
                break;
        }
        event.consume();
    }

    private void transition(Label label, double size, Color color) {

        applyFontAndColor(label, size, color);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), label);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(FadeTransition.INDEFINITE);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();
    }

    private void applyFontAndColor(Label label, double size, Color color) {
        Font pixelFont = Font.loadFont(getClass().getResourceAsStream("/fonts/PixelFont.ttf"), size);
        label.setFont(pixelFont);
        label.setTextFill(color);
    }

}
