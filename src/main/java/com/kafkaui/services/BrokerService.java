package com.kafkaui.services;

import com.kafkaui.models.BrokerModel;
import com.kafkaui.ui.components.BrokerTable.BrokerProps;

import java.util.ArrayList;

public class BrokerService {
    public static ArrayList<BrokerProps> modelPropsMapper(ArrayList<BrokerModel> brokers) {
        ArrayList<BrokerProps> modelProps = new ArrayList<>();
        for (BrokerModel b : brokers) {
            BrokerProps brokerProps = new BrokerProps(
                    String.valueOf(b.getId()),
                    b.getHost(),
                    String.valueOf(b.getPort())
            );
            modelProps.add(brokerProps);
        }
        return modelProps;
    }
}
