package com.kafkaui.models;

public class BrokerModel {
    private int id;
    private String host;
    private int port;

    public BrokerModel(int id, String host, int port) {
        this.id = id;
        this.host = host;
        this.port = port;
    }

    public int getId() {
        return id;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}
