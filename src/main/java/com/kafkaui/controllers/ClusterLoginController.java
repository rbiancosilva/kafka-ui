package com.kafkaui.controllers;

import com.kafkaui.clients.KafkaClusterLoginThread;
import com.kafkaui.models.ClusterLoginModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import org.apache.kafka.clients.admin.DescribeClusterResult;

import java.util.concurrent.ExecutionException;

public class ClusterLoginController {
    @FXML
    public TextField serverHost;
    @FXML
    public TextField serverPort;
    @FXML
    public TextField saslUsername;
    @FXML
    public TextField saslPassword;
    @FXML
    public RadioButton authenticationRadio;
    @FXML
    public Button helloButton;
    @FXML
    private Label welcomeText;

    @FXML
    public void initialize() {
        authenticationRadio.setSelected(false);
        saslUsername.setDisable(true);
        saslPassword.setDisable(true);
    }

    @FXML
    protected void onHelloButtonClick() {
        ClusterLoginModel clusterModel = new ClusterLoginModel();
        clusterModel.setHost(serverHost.getText());
        clusterModel.setPort(serverPort.getText());
        if (authenticationRadio.isSelected()) {
            clusterModel.setUser(saslUsername.getText());
            clusterModel.setPass(saslPassword.getText());
        }
        KafkaClusterLoginThread kafkaClusterLogin = new KafkaClusterLoginThread(clusterModel, welcomeText);
        new Thread(kafkaClusterLogin).start();
        helloButton.setDisable(false);
    }

    @FXML
    public void authenticationRadioClicked() {
        if (authenticationRadio.isSelected()) {
            saslPassword.setDisable(false);
            saslUsername.setDisable(false);
        } else {
            saslPassword.setDisable(true);
            saslUsername.setDisable(true);
        }
    }
}