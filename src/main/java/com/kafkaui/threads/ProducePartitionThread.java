package com.kafkaui.threads;

import com.kafkaui.context.ProducePartitionContext;
import com.kafkaui.context.PropertiesContext;
import com.kafkaui.context.TopicDetailContext;
import com.kafkaui.controllers.TopicDetailCardController;
import com.kafkaui.ui.components.ErrorWindow;
import javafx.application.Platform;
import javafx.concurrent.Task;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.TopicPartitionInfo;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Properties;

public class ProducePartitionThread extends Task {
    private TopicPartitionInfo topicPartitionInfo;

    public ProducePartitionThread(TopicPartitionInfo topicPartitionInfo) {
        this.topicPartitionInfo = topicPartitionInfo;
    }

    @Override
    protected Object call() {
        startProducing();
        return null;
    }

    private void startProducing() {
        Properties props = getProducerProperties();
        int partition = topicPartitionInfo.partition();
        String topicName = TopicDetailContext.gi().getCurrentTopic().name();

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        ProducePartitionContext.setProducer(producer);

        Platform.runLater(() -> {


        });

    }

    private Properties getProducerProperties() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, PropertiesContext.gi().getProperties().getProperty("bootstrap.servers"));
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        return props;
    }

    public TopicPartitionInfo getTopicPartitionInfo() {
        return topicPartitionInfo;
    }

    public void setTopicPartitionInfo(TopicPartitionInfo topicPartitionInfo) {
        this.topicPartitionInfo = topicPartitionInfo;
    }
}
