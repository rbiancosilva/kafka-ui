package com.kafkaui.context;

import org.apache.kafka.clients.admin.TopicListing;

import java.time.LocalDateTime;
import java.util.Collection;

public class TopicListContext {
    private Collection<TopicListing> topics;
    private TopicListContext() {}
    private static TopicListContext instance;
    private LocalDateTime lastUpdated;

    public static TopicListContext gi() {
        if (instance == null) {
            instance = new TopicListContext();
        }
        return instance;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public Collection<TopicListing> getTopics() {
        return topics;
    }

    public void setTopics(Collection<TopicListing> topics) {
        this.topics = topics;
        this.lastUpdated = LocalDateTime.now();
    }
}
