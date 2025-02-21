package com.kafkaui.context;

import com.kafkaui.threads.ConsumePartitionThread;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartitionInfo;

public class ConsumePartitionContext {
    private static ConsumePartitionContext instance;

    private ConsumePartitionContext() {}

    public static ConsumePartitionContext gi() {
        if (instance == null) {
            instance = new ConsumePartitionContext();
        }
        return instance;
    }
}
