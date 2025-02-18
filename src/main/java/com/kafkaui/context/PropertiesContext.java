package com.kafkaui.context;

import java.util.Properties;

public class PropertiesContext {
    private Properties properties;

    private static PropertiesContext instance;

    private PropertiesContext() {}

    public static PropertiesContext gi() {
        if (instance == null) {
            instance = new PropertiesContext();
        }
        return instance;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
