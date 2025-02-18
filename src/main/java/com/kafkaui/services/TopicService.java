package com.kafkaui.services;

import com.kafkaui.ui.components.TopicTable;
import org.apache.kafka.clients.admin.TopicListing;

import java.util.ArrayList;
import java.util.Collection;

public class TopicService {

    public static ArrayList<TopicTable.TopicProps> modelPropsMapper(Collection<TopicListing> topics) {
        ArrayList<TopicTable.TopicProps> modelProps = new ArrayList<>();
        for (TopicListing t : topics) {
            TopicTable.TopicProps topicProps = new TopicTable.TopicProps(
                    String.valueOf(t.topicId()),
                    t.name()
            );
            modelProps.add(topicProps);
        }
        return modelProps;
    }


}
