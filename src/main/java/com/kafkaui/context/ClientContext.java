package com.kafkaui.context;

import org.apache.kafka.clients.admin.AdminClient;

public class ClientContext {
    private static AdminClient adminClient;

    public static AdminClient getAdminClient() {
        return adminClient;
    }

    public static void setAdminClient(AdminClient adminClient) {
        ClientContext.adminClient = adminClient;
    }
}
