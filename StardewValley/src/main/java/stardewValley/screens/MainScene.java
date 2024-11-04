package stardewValley.screens;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.animation.FadeTransition;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import stardewValley.control.Controller;
import stardewValley.ui.StardewValley;

public class MainScene {
    @FXML
    private StackPane rootVBox;

    @FXML
    private Label pressEnterLabel;

    @FXML
    private ImageView backgroundImage; // Background

    @FXML
    public void initialize() {

        backgroundImage = new ImageView(new Image(getClass().getResourceAsStream("/img/background/background.png")));


        rootVBox.getChildren().add(0, backgroundImage);


        Font pixelFont = Font.loadFont(getClass().getResourceAsStream("/fonts/PixelFont.ttf"), 25);

        applyFontAndColor(pressEnterLabel, pixelFont, Color.WHITE);
        pressEnterLabel.setTranslateY(-10);


        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), pressEnterLabel);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(FadeTransition.INDEFINITE);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();


        rootVBox.requestFocus();


        rootVBox.widthProperty().addListener((obs, oldVal, newVal) -> {
            backgroundImage.setFitWidth(rootVBox.getWidth());
        });
        rootVBox.heightProperty().addListener((obs, oldVal, newVal) -> {
            backgroundImage.setFitHeight(rootVBox.getHeight());
        });


        rootVBox.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ENTER:
                    StardewValley.getInstance().changeScene(2);
                    break;
                default:
                    break;
            }
            event.consume();
        });

        Platform.runLater(() -> rootVBox.requestFocus());

    }

    private void applyFontAndColor(Label label, Font font, Color color) {
        label.setFont(font);
        label.setTextFill(color);
    }
}
