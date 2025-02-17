package com.kafkaui.clients;

import com.kafkaui.context.ClientContext;
import com.kafkaui.context.TopicListContext;
import com.kafkaui.ui.components.ErrorWindow;
import javafx.application.Platform;
import javafx.concurrent.Task;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;

public class ListTopicsThread extends Task {

    @Override
    protected Object call() throws Exception {
        getTopics();
        return null;
    }

    private void getTopics() {
        AdminClient adminClient = ClientContext.getAdminClient();
        if (adminClient == null) {
            ErrorWindow.popErrorWindow("No client Logged in");
            return;
        }

        ListTopicsResult topics = adminClient.listTopics();
        topics.listings().whenComplete((topicList, exception) -> {
            if (exception == null) {
                TopicListContext.setTopics(topicList);

            }else {
                Platform.runLater(() -> {
                    ErrorWindow.popErrorWindow(exception.getMessage());
                });
            }
        });
    }
}
