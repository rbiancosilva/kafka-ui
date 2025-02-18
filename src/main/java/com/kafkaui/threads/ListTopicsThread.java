package com.kafkaui.threads;

import com.kafkaui.context.ClientContext;
import com.kafkaui.context.StageContext;
import com.kafkaui.context.TopicListContext;
import com.kafkaui.ui.components.ErrorWindow;
import com.kafkaui.ui.pages.TopicsPage;
import javafx.application.Platform;
import javafx.concurrent.Task;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;

import java.io.IOException;
import java.time.LocalDateTime;

public class ListTopicsThread extends Task {

    @Override
    protected Object call() {
        getTopics();
        return null;
    }

    private void getTopics() {
        LocalDateTime lastUpdate = TopicListContext.gi().getLastUpdated();
        LocalDateTime now = LocalDateTime.now();

        //Checks if it has been 3 minutes since the last topic list update
        if (lastUpdate != null && lastUpdate.isAfter(now.minusMinutes(3))) {
            return; //If the list was updated less than 3 minutes ago, then it isn't updated again
        }

        //If it has been more than three minutes since the last update, then the list is updated
        AdminClient adminClient = ClientContext.gi().getAdminClient();
        if (adminClient == null) {
            ErrorWindow.popErrorWindow("No client Logged in");
            return;
        }

        ListTopicsResult topics = adminClient.listTopics();
        topics.listings().whenComplete((topicList, exception) -> {
            if (exception == null) {
                Platform.runLater(() -> {
                    try {
                        TopicListContext.gi().setTopics(topicList);
                        TopicsPage.show();
                        StageContext.OVERVIEW.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

            }else {
                Platform.runLater(() -> {
                    ErrorWindow.popErrorWindow(exception.getMessage());
                });
            }
        });
    }
}
