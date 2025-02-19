package com.kafkaui.context;

import org.apache.kafka.clients.admin.TopicDescription;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class TopicDetailContext {
    private TopicDescription currentTopic;
    private final Map<String, Map<LocalDateTime, TopicDescription>> topicDetailMap = new HashMap<>();
    private static TopicDetailContext instance;

    public static TopicDetailContext gi() {
        if (instance == null) {
            instance = new TopicDetailContext();
        }
        return instance;
    }
    private TopicDetailContext() {}

    public Map<String, Map<LocalDateTime, TopicDescription>> getTopicDetailMap() {
        return topicDetailMap;
    }

    public boolean isCached(String topicName) {
        if (topicDetailMap.containsKey(topicName)) {
            Map<LocalDateTime, TopicDescription> map = topicDetailMap.get(topicName);
            LocalDateTime lastUpdate = map.keySet().stream().findFirst().get();
            return lastUpdate.isAfter(LocalDateTime.now().minusMinutes(3));
        }
        return false;
    }

    public TopicDescription getCurrentTopic() {
        return currentTopic;
    }

    public void setCurrentTopic(String topicName) {
        Map<LocalDateTime, TopicDescription> map = topicDetailMap.get(topicName);
        TopicDescription topic = map.values().stream().findFirst().get();
        this.currentTopic = topic;
    }

    public void setCurrentTopic(TopicDescription topic) {
        this.currentTopic = topic;
    }

    public void addToContext(String topicName, TopicDescription topicDescription) {
        this.topicDetailMap.put(topicName, new HashMap<>());
        this.topicDetailMap.get(topicName).put(LocalDateTime.now(), topicDescription);
    }

}
