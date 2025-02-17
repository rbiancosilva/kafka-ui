package com.kafkaui.controllers;

import com.kafkaui.ui.components.BrokerTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class OverviewController {
    @FXML
    public TableView table;
    @FXML
    public TableColumn idColumn;

    @FXML
    public TableColumn hostColumn;
    @FXML
    public TableColumn portColumn;

    @FXML
    public void initialize() {
        BrokerTable.fillTable(
                table,
                idColumn,
                hostColumn,
                portColumn
        );
    }

    @FXML
    public void handleBrokersAction(ActionEvent actionEvent) {
    }

    @FXML
    public void handleTopicsAction(ActionEvent actionEvent) {
    }

    @FXML
    public void handleConnectorsAction(ActionEvent actionEvent) {
    }

    @FXML
    public void handleConsumerGroupsAction(ActionEvent actionEvent) {
    }
}
