package com.example.bibliotecaescolar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/bibliotecaescolar/views/menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        scene.getStylesheets().add(getClass().getResource("/com/example/bibliotecaescolar/views/style.css").toExternalForm());
        stage.setTitle("Library");
        stage.setScene(scene);
        stage.show();
    }
}
