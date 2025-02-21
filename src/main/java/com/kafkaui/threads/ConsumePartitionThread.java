package com.kafkaui.threads;

import com.kafkaui.context.TopicPartitionContext;
import com.kafkaui.ui.pages.ConsumePartitionPage;
import javafx.application.Platform;
import javafx.concurrent.Task;
import org.apache.kafka.common.TopicPartitionInfo;

import java.io.IOException;

public class ConsumePartitionThread extends Task {
    public ConsumePartitionThread(TopicPartitionInfo topicPartitionInfo) {
        TopicPartitionContext.gi().setTopicPartitionInfo(topicPartitionInfo);
    }

    @Override
    protected Object call() {
        startConsuming();
        return null;
    }

    private void startConsuming() {
        Platform.runLater(() -> {
            try {
                ConsumePartitionPage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

    }
}
