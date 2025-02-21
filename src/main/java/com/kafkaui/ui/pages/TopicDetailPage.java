package com.kafkaui.ui.pages;

import com.kafkaui.ClusterLoginApplication;
import com.kafkaui.context.StageContext;
import com.kafkaui.context.TopicDetailContext;
import com.kafkaui.ui.components.TopicTable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class TopicDetailPage {
    public static void show() throws IOException {
        Stage stage = new Stage();
        stage.setTitle(TopicDetailContext.gi().getCurrentTopic().name() + " - Partitions");
        Parent root = FXMLLoader.load(Objects.requireNonNull(ClusterLoginApplication.class.getResource("topic-details-container.fxml")));
        Scene scene = new Scene(root, 750, 400);
        stage.setScene(scene);
        stage.show();
        StageContext.TOPIC_DETAILS = stage;
        stage.setOnHidden(e -> {
            TopicTable.topicTableContext.setDisable(false);
            StageContext.TOPIC_DETAILS = null;
        });
    }
}
