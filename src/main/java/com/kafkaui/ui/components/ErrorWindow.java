package com.kafkaui.ui.components;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class ErrorWindow {
    public static void popErrorWindow(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
