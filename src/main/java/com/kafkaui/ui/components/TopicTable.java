package com.kafkaui.ui.components;

import com.kafkaui.context.StageContext;
import com.kafkaui.context.TopicListContext;
import com.kafkaui.services.TopicService;
import com.kafkaui.threads.DetailTopicThread;
import com.kafkaui.ui.pages.TopicDetailPage;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;

public class TopicTable {
    public static void fillTable(TableView<TopicTable.TopicProps> table, TableColumn<TopicTable.TopicProps, String> id, TableColumn<BrokerTable.BrokerProps, String> name) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        table.setItems(getTopicProps());

        table.setRowFactory(tv -> {
            TableRow<TopicProps> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    try {
                        TopicProps topic = row.getItem();
                        DetailTopicThread detailTopicThread = new DetailTopicThread(topic.getName());
                        new Thread(detailTopicThread).start();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            return row;
        });
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
        ArrayList<TopicTable.TopicProps> topicList = TopicService.modelPropsMapper(TopicListContext.gi().getTopics());
        return FXCollections.observableArrayList(topicList);
    }
}
