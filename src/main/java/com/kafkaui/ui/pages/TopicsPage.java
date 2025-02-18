package com.kafkaui.ui.pages;

import com.kafkaui.ClusterLoginApplication;
import com.kafkaui.context.StageContext;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TopicsPage {
    public static void show() throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Topics");
        stage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(ClusterLoginApplication.class.getResource("topics-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        stage.setScene(scene);
        stage.show();
        StageContext.TOPICS = stage;
    }
}
