package com.kafkaui.context;

import org.apache.kafka.clients.admin.AdminClient;

public class ClientContext {
    private AdminClient adminClient;

    private static ClientContext instance;

    private ClientContext() {
    }

    public static ClientContext gi() {
        if (instance == null) {
            instance = new ClientContext();
        }
        return instance;
    }

    public AdminClient getAdminClient() {
        return adminClient;
    }

    public void setAdminClient(AdminClient adminClient) {
        this.adminClient = adminClient;
    }
}
