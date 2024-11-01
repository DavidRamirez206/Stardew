package stardewValley.screens;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import stardewValley.control.SceneManager;

public class Scene3Controller extends SceneBase {

    @FXML
    private Text scoreText;
    @FXML
    private Text sceneTitleText;
    @FXML
    private ImageView finalImage;

    private SceneManager sceneManager;

    public void initialize() {
        // Set the title or any end-of-game information
        sceneTitleText.setText("Escena Final");
        finalImage.setImage(new Image("path/to/final_image.png"));
    }

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @FXML
    private void goToPrevious() {
        sceneManager.switchToScene2();
    }

    @FXML
    private void finishGame() {
        sceneManager.endGame();
    }

    public void updateScore(int score) {
        scoreText.setText(String.valueOf(score));
    }
}
