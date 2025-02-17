package com.kafkaui.context;

import org.apache.kafka.clients.admin.TopicListing;

import java.util.Collection;

public class TopicListContext {
    private static Collection<TopicListing> topics;

    public static Collection<TopicListing> getTopics() {
        return topics;
    }

    public static void setTopics(Collection<TopicListing> topics) {
        TopicListContext.topics = topics;
    }
}
