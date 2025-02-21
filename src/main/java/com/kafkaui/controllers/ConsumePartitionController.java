package com.kafkaui.controllers;

import com.kafkaui.context.PropertiesContext;
import com.kafkaui.context.TopicDetailContext;
import com.kafkaui.context.TopicPartitionContext;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Properties;

public class ConsumePartitionController {
    private static KafkaConsumer<String, String> consumer;
    @FXML
    public TextArea consumeArea;

    @FXML
    public void initialize() {
        int partition = TopicPartitionContext.gi().getTopicPartitionInfo().partition();
        String topicName = TopicDetailContext.gi().getCurrentTopic().name();
        Properties baseProperties = PropertiesContext.gi().getProperties();
        ConsumeThread consumeThread = new ConsumeThread(
                baseProperties,
                partition,
                topicName,
                consumeArea
        );
        new Thread(consumeThread).start();
    }

    private class ConsumeThread extends Task {
        private Properties baseProperties;
        private int partition;
        private String topicName;
        private TextArea consumeArea;

        public ConsumeThread(
                Properties baseProperties,
                int partition,
                String topicName,
                TextArea consumeArea
        ) {
            this.partition = partition;
            this.topicName = topicName;
            this.baseProperties = baseProperties;
            this.consumeArea = consumeArea;
        }

        @Override
        public Object call() {
            Properties consumerProperties = getConsumerProperties();

            KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerProperties);
            TopicPartition topicPartition = new TopicPartition(topicName, partition);
            consumer.assign(Collections.singleton(topicPartition));

            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                StringBuilder batch = new StringBuilder();
                for (ConsumerRecord<String, String> record : records) {
                    batch.append(LocalDateTime.now()).append(" - ");
                    batch.append(record.value()).append("\n");
                }
                if (!batch.isEmpty()) {
                    Platform.runLater(() -> consumeArea.appendText(batch.toString()));
                }
            }

        }

        private Properties getConsumerProperties() {
            Properties props = new Properties();
            String consumerGroup = "kafka-ui";

            props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, baseProperties.get("bootstrap.servers"));
            props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
            props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
            props.put("security.protocol", baseProperties.get("security.protocol"));
            props.put("sasl.mechanism", baseProperties.get("sasl.mechanism"));
            props.put("sasl.jaas.config", baseProperties.get("sasl.jaas.config"));
            props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
            props.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroup);

            return props;
        }

    }
}
