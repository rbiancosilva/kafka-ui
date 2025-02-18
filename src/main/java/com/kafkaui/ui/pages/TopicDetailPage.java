package com.kafkaui.ui.pages;

import com.kafkaui.ClusterLoginApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TopicDetailPage {
    public static void show(String topicName) throws IOException {
        Stage stage = new Stage();
        stage.setTitle(topicName);
        FXMLLoader fxmlLoader = new FXMLLoader(ClusterLoginApplication.class.getResource("topic-detail-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        stage.setScene(scene);
        stage.show();
    }
}
