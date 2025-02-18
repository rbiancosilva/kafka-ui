package com.kafkaui.ui.components;

import com.kafkaui.context.BrokerContext;
import com.kafkaui.services.BrokerService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class BrokerTable {

    public static void fillTable(TableView<BrokerProps> table, TableColumn<BrokerProps, String> id, TableColumn<BrokerProps, String> host, TableColumn<BrokerProps, String> port) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        host.setCellValueFactory(new PropertyValueFactory<>("host"));
        port.setCellValueFactory(new PropertyValueFactory<>("port"));
        table.setItems(getBrokerProps());
    }

    public static class BrokerProps {
        private final SimpleStringProperty id;
        private final SimpleStringProperty host;
        private final SimpleStringProperty port;

        public BrokerProps(String id, String host, String port) {
            this.id = new SimpleStringProperty(id);
            this.host = new SimpleStringProperty(host);
            this.port = new SimpleStringProperty(port);
        }

        public String getHost() {
            return host.get();
        }
        public String getPort() {
            return port.get();
        }

        public String getId() {
            return id.get();
        }

    }

    private static ObservableList<BrokerProps> getBrokerProps() {
        ArrayList<BrokerProps> brokerList = BrokerService.modelPropsMapper(BrokerContext.gi().getBrokers());
        return FXCollections.observableArrayList(brokerList);
    }

}
