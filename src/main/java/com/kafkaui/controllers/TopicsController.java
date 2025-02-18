package com.kafkaui.controllers;

import com.kafkaui.ui.components.TopicTable;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TopicsController {
    @FXML
    public TableView table;

    @FXML
    public TableColumn nameColumn;

    @FXML
    public TableColumn idColumn;

    @FXML
    public void initialize() {
        TopicTable.fillTable(
                table,
                idColumn,
                nameColumn
        );
    }

}
