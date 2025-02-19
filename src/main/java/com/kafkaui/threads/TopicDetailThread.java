package com.kafkaui.threads;

import com.kafkaui.context.ClientContext;
import com.kafkaui.context.TopicDetailContext;
import com.kafkaui.ui.components.ErrorWindow;
import com.kafkaui.ui.pages.TopicDetailPage;
import javafx.application.Platform;
import javafx.concurrent.Task;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.common.KafkaFuture;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class TopicDetailThread extends Task {
    private String topicName;

    public TopicDetailThread(String topicName) {
        this.topicName = topicName;
    }

    @Override
    protected Object call() throws ExecutionException, InterruptedException {
        getDetailTopic();
        return null;
    }

    private void getDetailTopic() {
        if (TopicDetailContext.gi().isCached(topicName)) {
            TopicDetailContext.gi().setCurrentTopic(topicName);
        } else {
            try {
                AdminClient adminClient = ClientContext.gi().getAdminClient();
                DescribeTopicsResult result = adminClient.describeTopics(Collections.singleton(topicName));

                for (Map.Entry<String, KafkaFuture<TopicDescription>> topic : result.topicNameValues().entrySet()) {
                    String topicName = topic.getKey();
                    TopicDescription topicDescription = topic.getValue().get();
                    TopicDetailContext.gi().addToContext(topicName, topicDescription);
                    TopicDetailContext.gi().setCurrentTopic(topicDescription);
                }
            } catch (Exception e) {
                ErrorWindow.popErrorWindow(e.getMessage());
            }
        }
        Platform.runLater(() -> {
            try {
                TopicDetailPage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
