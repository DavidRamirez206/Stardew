module stardewvalley {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;


    opens stardewValley.control to javafx.fxml;
    exports stardewValley.control;

    opens stardewValley.ui to javafx.fxml;
    exports stardewValley.ui;

    opens stardewValley.screens to javafx.fxml;
    exports stardewValley.screens;
    exports stardewValley.model;
    opens stardewValley.model to javafx.fxml;
}