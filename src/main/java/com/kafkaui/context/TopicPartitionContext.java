package com.kafkaui.context;

import org.apache.kafka.common.TopicPartitionInfo;

public class TopicPartitionContext {
    private TopicPartitionInfo topicPartitionInfo;
    private static TopicPartitionContext instance;
    private TopicPartitionContext() {}

    public static TopicPartitionContext gi() {
        if (instance == null) {
            instance = new TopicPartitionContext();
        }
        return instance;
    }

    public TopicPartitionInfo getTopicPartitionInfo() {
        return topicPartitionInfo;
    }

    public void setTopicPartitionInfo(TopicPartitionInfo topicPartitionInfo) {
        this.topicPartitionInfo = topicPartitionInfo;
    }
}
