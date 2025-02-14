package com.kafkaui;

import com.kafkaui.context.StageContext;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClusterLoginApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClusterLoginApplication.class.getResource("add-cluster-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 400);
        stage.setTitle("Add cluster");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        StageContext.CLUSTER_LOGIN = stage;
    }

    public static void main(String[] args) {
        launch();
    }
}