module com.kafkaui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires kafka.clients;
    requires java.desktop;

    opens com.kafkaui to javafx.fxml;
    exports com.kafkaui;
    exports com.kafkaui.controllers;
    exports com.kafkaui.models;
    exports com.kafkaui.ui.components;
    opens com.kafkaui.controllers to javafx.fxml;
}