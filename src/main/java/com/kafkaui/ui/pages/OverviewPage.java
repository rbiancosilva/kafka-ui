package com.kafkaui.ui.pages;

import com.kafkaui.ClusterLoginApplication;
import com.kafkaui.context.BrokerContext;
import com.kafkaui.context.StageContext;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.kafka.common.Node;

import java.io.IOException;
import java.util.Collection;

public class OverviewPage {
    public static void show() throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Kafka UI");
        stage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(ClusterLoginApplication.class.getResource("overview-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 760);
        stage.setScene(scene);
        stage.show();
        StageContext.OVERVIEW = stage;
    }
}
