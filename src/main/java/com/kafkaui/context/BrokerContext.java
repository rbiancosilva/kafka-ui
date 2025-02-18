package com.kafkaui.context;

import org.apache.kafka.common.Node;

import java.util.ArrayList;
import java.util.Collection;

public class BrokerContext {
    private Collection<Node> brokers = new ArrayList<>();

    private BrokerContext() {}

    private static BrokerContext instance;

    public static BrokerContext gi() {
        if (instance == null) {
            instance = new BrokerContext();
        }
        return instance;
    }

    public void addBroker(Node broker) {
        brokers.add(broker);
    }

    public Collection<Node> getBrokers() {
        return brokers;
    }

    public void setBrokers(Collection<Node> nodes) {
        this.brokers = nodes;
    }

}
