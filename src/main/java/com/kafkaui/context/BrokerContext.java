package com.kafkaui.context;

import com.kafkaui.models.BrokerModel;
import org.apache.kafka.common.Node;

import java.util.ArrayList;
import java.util.Collection;

public class BrokerContext {
    private static final ArrayList<BrokerModel> brokers = new ArrayList<>();

    public static void addBroker(BrokerModel broker) {
        brokers.add(broker);
    }

    public static ArrayList<BrokerModel> getBrokers() {
        return brokers;
    }

    public static void fillBrokers(Collection<Node> nodes) {
        for (Node node : nodes) {
            BrokerModel broker = new BrokerModel(
                    node.id(),
                    node.host(),
                    node.port()
            );
            brokers.add(broker);
        }
    }

}
