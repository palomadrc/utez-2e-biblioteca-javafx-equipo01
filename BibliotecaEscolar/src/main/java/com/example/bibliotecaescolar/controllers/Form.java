package com.example.bibliotecaescolar.controllers;

import com.example.bibliotecaescolar.repository.BookRepo;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class Form {

    @FXML private TextField txtISBN;
    @FXML private TextField txtTitle;
    @FXML private TextField txtAuthor;
    @FXML private TextField txtYear;
    @FXML private TextField txtEditorial;
    @FXML private ComboBox<String> comboGender;
    @FXML private ComboBox<String> comboAvailable;

    private BookRepo repo = new BookRepo();

    @FXML
    private void initialize(){
        comboGender.getItems().addAll("Drama, Romace, History, CiFi, Literature, Science, Fantacy, Horror");
        comboAvailable.getItems().addAll("Yes, No");

    }

    @FXML //guardar el libro y tambien editar (las dos validaciones)
    private void Save(){

    }

    @FXML //Este es para que se regrese al menu-view.fxml al picarle
    private void Cancel(){

    }
}
