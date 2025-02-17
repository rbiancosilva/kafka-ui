package com.kafkaui.clients;

import com.kafkaui.context.BrokerContext;
import com.kafkaui.context.ClientContext;
import com.kafkaui.context.StageContext;
import com.kafkaui.models.ClusterLoginModel;
import com.kafkaui.ui.components.ErrorWindow;
import com.kafkaui.ui.pages.OverviewPage;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.kafka.clients.admin.*;
import javafx.scene.control.Label;
import javafx.concurrent.Task;

import java.io.IOException;
import java.util.Properties;

public class KafkaClusterLoginThread extends Task {
    private final ClusterLoginModel clusterLoginModel;

    public KafkaClusterLoginThread(ClusterLoginModel clusterLoginModel) {
        this.clusterLoginModel = clusterLoginModel;
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
                    try {
                        BrokerContext.setBrokers(nodes);
                        OverviewPage.show();
                        StageContext.CLUSTER_LOGIN.close();
                        ClientContext.setAdminClient(adminClient);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            } else {
                Platform.runLater(() -> {
                    ErrorWindow.popErrorWindow(exception.getMessage());
                });
            }
        });

    }
}
