module com.example.bibliotecaescolar {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.bibliotecaescolar to javafx.fxml;
    opens com.example.bibliotecaescolar.model to javafx.fxml;
    opens com.example.bibliotecaescolar.controllers to javafx.fxml;
    opens com.example.bibliotecaescolar.repository to javafx.fxml;

    exports com.example.bibliotecaescolar;
    exports com.example.bibliotecaescolar.model;
    exports com.example.bibliotecaescolar.controllers;
    exports com.example.bibliotecaescolar.repository;
}