package com.kafkaui.controllers;

import com.kafkaui.threads.ListTopicsThread;
import com.kafkaui.ui.components.BrokerTable;
import com.kafkaui.ui.components.ErrorWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class OverviewController {
//    @FXML
//    public void initialize() {
//        BrokerTable.fillTable(
//                table,
//                idColumn,
//                hostColumn,
//                portColumn
//        );
//    }

    @FXML
    public void handleBrokersAction(ActionEvent actionEvent) {
    }

    @FXML
    public void handleTopicsAction() {
        ErrorWindow.popErrorWindow("Teste");
        ListTopicsThread listTopicsThread = new ListTopicsThread();
        new Thread(listTopicsThread).start();
    }

    @FXML
    public void handleConnectorsAction(ActionEvent actionEvent) {
    }

    @FXML
    public void handleConsumerGroupsAction(ActionEvent actionEvent) {
    }
}
