package com.kafkaui.ui.pages;

import com.kafkaui.ClusterLoginApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ConsumePartitionPage {
    public static void show() throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Consume Partition");
        stage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(ClusterLoginApplication.class.getResource("consume-partition.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setScene(scene);
        stage.show();
    }
}
