package com.kafkaui.ui.components;

import com.kafkaui.context.BrokerContext;
import com.kafkaui.context.TopicListContext;
import com.kafkaui.services.BrokerService;
import com.kafkaui.services.TopicListService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.kafka.clients.admin.TopicListing;

import java.util.ArrayList;

public class TopicTable {
    public static void fillTable(TableView<TopicTable.TopicProps> table, TableColumn<TopicTable.TopicProps, String> id, TableColumn<BrokerTable.BrokerProps, String> name) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        table.setItems(getTopicProps());
    }

    public static class TopicProps {
        private final SimpleStringProperty id;
        private final SimpleStringProperty name;

        public TopicProps(String id, String host) {
            this.id = new SimpleStringProperty(id);
            this.name = new SimpleStringProperty(host);
        }

        public String getName() {
            return name.get();
        }

        public String getId() {
            return id.get();
        }

    }

    private static ObservableList<TopicTable.TopicProps> getTopicProps() {
        ArrayList<TopicTable.TopicProps> topicList = TopicListService.modelPropsMapper(TopicListContext.gi().getTopics());
        return FXCollections.observableArrayList(topicList);
    }
}
