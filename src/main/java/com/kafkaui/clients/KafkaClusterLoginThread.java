package com.kafkaui.clients;

import com.kafkaui.models.ClusterLoginModel;
import javafx.application.Platform;
import org.apache.kafka.clients.admin.*;
import javafx.scene.control.Label;
import javafx.concurrent.Task;

import java.util.Properties;

public class KafkaClusterLoginThread extends Task {
    private final ClusterLoginModel clusterLoginModel;
    private final Label labelContext;

    public KafkaClusterLoginThread(ClusterLoginModel clusterLoginModel, Label labelContext) {
        this.clusterLoginModel = clusterLoginModel;
        this.labelContext = labelContext;
    }

    @Override
    public Object call() {
        getNodes(clusterLoginModel);
        return null;
    }

    public void getNodes(ClusterLoginModel clusterLoginModel) {
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, clusterLoginModel.getHost() + ":" + clusterLoginModel.getPort());
        props.put(AdminClientConfig.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
        props.put("sasl.mechanism", "PLAIN");
        props.put("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required " +
                "username=\"" + clusterLoginModel.getUser() + "\" password=\"" + clusterLoginModel.getPass() + "\";");
        AdminClient adminClient = AdminClient.create(props);
        adminClient.describeCluster().nodes().whenComplete((nodes, exception) -> {
            if (exception == null) {
                Platform.runLater(() -> {

                    labelContext.setText(nodes.toString());
                });
            } else {
                Platform.runLater(() -> {
                    labelContext.setText(exception.getMessage());
                });
            }
        });

    }
}
