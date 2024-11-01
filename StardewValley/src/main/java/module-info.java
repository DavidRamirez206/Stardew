module stardewvalley {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens stardewValley.control to javafx.fxml;
    exports stardewValley.control;

    opens stardewValley.ui to javafx.fxml;
    exports stardewValley.ui;

    opens stardewValley.screens to javafx.fxml;
    exports stardewValley.screens;
}