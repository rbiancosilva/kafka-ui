package com.kafkaui.controllers;

import com.kafkaui.ClusterLoginApplication;
import com.kafkaui.context.TopicDetailContext;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.common.TopicPartitionInfo;

public class TopicDetailController {
    @FXML
    public FlowPane testContainer;

    public void initialize() {
        TopicDescription topics = TopicDetailContext.gi().getCurrentTopic();

        int partitions = topics.partitions().size();

        for (int i = 0; i < partitions; i++) {
            addPartition(topics.partitions().get(i));
        }
    }
    private void addPartition(TopicPartitionInfo topicPartition) {
        try {

            FXMLLoader loader = new FXMLLoader(ClusterLoginApplication.class.getResource("topic-detail-card.fxml"));
            HBox card = loader.load();


            TopicDetailCardController cardController = loader.getController();
            cardController.setCardData(topicPartition);


            testContainer.getChildren().add(card);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
