package com.kafkaui.services;

import com.kafkaui.ui.components.BrokerTable.BrokerProps;
import org.apache.kafka.common.Node;

import java.util.ArrayList;
import java.util.Collection;

public class BrokerService {
    public static ArrayList<BrokerProps> modelPropsMapper(Collection<Node> brokers) {
        ArrayList<BrokerProps> modelProps = new ArrayList<>();
        for (Node b : brokers) {
            BrokerProps brokerProps = new BrokerProps(
                    String.valueOf(b.id()),
                    b.host(),
                    String.valueOf(b.port())
            );
            modelProps.add(brokerProps);
        }
        return modelProps;
    }
}
