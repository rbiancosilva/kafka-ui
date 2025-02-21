package com.kafkaui.controllers;

import com.kafkaui.threads.ConsumePartitionThread;
import com.kafkaui.threads.ProducePartitionThread;
import com.kafkaui.ui.components.BrokerTable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import org.apache.kafka.common.TopicPartitionInfo;

public class TopicDetailCardController {
    @FXML
    public TableView tableOne;
    @FXML
    public TableColumn idColumnOne;
    @FXML
    public TableColumn hostColumnOne;
    @FXML
    public TableView tableTwo;
    @FXML
    public TableColumn idColumnTwo;
    @FXML
    public TableColumn hostColumnTwo;
    @FXML
    public TableColumn portColumnOne;
    @FXML
    public TableColumn portColumnTwo;
    @FXML
    public Label partitionLabel;
    @FXML
    public Label leaderLabel;
    @FXML
    public Label leaderHost;
    @FXML
    public Label leaderPort;
    @FXML
    public Button consumeButton;
    @FXML
    public Button produceButton;
    @FXML
    private HBox cardRoot;


    private TopicPartitionInfo topicPartitionInfo;

    public void setCardData(TopicPartitionInfo topicPartitionInfo) {
        this.topicPartitionInfo = topicPartitionInfo;
        partitionLabel.setText("Partition: " + topicPartitionInfo.partition());
        leaderLabel.setText("Leader ID: " + topicPartitionInfo.leader().id());
        leaderHost.setText("Leader host: " + topicPartitionInfo.leader().host());
        leaderPort.setText("Leader port: " + topicPartitionInfo.leader().port());
        BrokerTable.fillTable( //Add right table to be filled
                tableOne,
                idColumnOne,
                hostColumnOne,
                portColumnOne,
                topicPartitionInfo.replicas()
        );
        BrokerTable.fillTable( //Add right table to be filled
                tableTwo,
                idColumnTwo,
                hostColumnTwo,
                portColumnTwo,
                topicPartitionInfo.isr()
        );
    }

    public HBox getCardRoot() {
        return cardRoot;
    }

    @FXML
    public void onProduceButtonClick() {
        ProducePartitionThread producePartitionThread = new ProducePartitionThread(topicPartitionInfo);
        new Thread(producePartitionThread).start();
    }

    @FXML
    public void onConsumeButtonClick() {
        ConsumePartitionThread consumePartitionThread = new ConsumePartitionThread(topicPartitionInfo);
        new Thread(consumePartitionThread).start();
    }

    public TopicPartitionInfo getTopicPartitionInfo() {
        return topicPartitionInfo;
    }

    public void setTopicPartitionInfo(TopicPartitionInfo topicPartitionInfo) {
        this.topicPartitionInfo = topicPartitionInfo;
    }
}
