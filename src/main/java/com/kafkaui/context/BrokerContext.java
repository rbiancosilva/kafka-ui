package com.kafkaui.context;

import org.apache.kafka.common.Node;

import java.util.ArrayList;
import java.util.Collection;

public class BrokerContext {
    private static Collection<Node> brokers = new ArrayList<>();

    public static void addBroker(Node broker) {
        brokers.add(broker);
    }

    public static Collection<Node> getBrokers() {
        return brokers;
    }

    public static void setBrokers(Collection<Node> nodes) {
        brokers = nodes;
    }

}
