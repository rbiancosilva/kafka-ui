package com.kafkaui.context;

import org.apache.kafka.clients.producer.KafkaProducer;

public class ProducePartitionContext {
    private static KafkaProducer<String, String> producer;
    private static ProducePartitionContext instance;
    private ProducePartitionContext() {}


    public static ProducePartitionContext gi() {
        if (instance == null) {
            instance = new ProducePartitionContext();
        }
        return instance;
    }

    public static KafkaProducer<String, String> getProducer() {
        return producer;
    }

    public static void setProducer(KafkaProducer<String, String> producer) {
        ProducePartitionContext.producer = producer;
    }
}
