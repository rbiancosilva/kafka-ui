package com.kafkaui.controllers;

import com.kafkaui.ui.components.BrokerTable;
import javafx.fxml.FXML;
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
    public Label label;
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
    private HBox cardRoot;


    public void setCardData(TopicPartitionInfo topicPartitionInfo) {
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
}
