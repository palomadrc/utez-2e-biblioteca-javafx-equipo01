package com.example.bibliotecaescolar.controllers;

import com.example.bibliotecaescolar.repository.BookRepo;
import com.example.bibliotecaescolar.model.Book;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.stream.Stream;

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
        comboGender.getItems().addAll("Drama", "Romace", "History", "CiFi", "Literature", "Science", "Fantacy", "Horror");
        comboAvailable.getItems().addAll("Yes", "No");

    }

    @FXML //guardar el libro y tambien editar (las dos validaciones)
    private void Save(){
       if (!validate()) return;

       List<String> data = Stream.of(txtISBN,txtAuthor,txtYear,txtTitle,txtEditorial).map(f ->f.getText().trim()).toList();
       String gender = comboGender.getValue();
       String available = comboAvailable.getValue();

       Book book = new Book(data.get(0),data.get(1),data.get(2),data.get(3),data.get(4),available,gender);

       repo.
    }

    private boolean validate(){
        try {
            boolean fieldVoid = List.of(txtTitle, txtAuthor, txtEditorial).stream().anyMatch(f -> f.getText().trim().isEmpty());

            boolean comboGendervoid = List.of(comboGender, comboAvailable).stream().anyMatch(c -> c.getSelectionModel().getSelectedItem().trim().isEmpty());

            int yearInt=Integer.parseInt(txtYear.getText());
            boolean yearCorrect = yearInt>=1500;

            boolean aTCorrect = txtTitle.getText().length() >= 3 && txtAuthor.getText().length() >= 3;
            if (fieldVoid || comboGendervoid) {
                throw new IllegalArgumentException("Campos vacios");
            }
            if (!yearCorrect || aTCorrect) {
                throw new IllegalArgumentException("parametros fuera de rango");
            }
            return true;
        }catch (IllegalArgumentException e){
            showMenssage(e);
            return false;
        }
    }

    private void showMenssage(IllegalArgumentException e){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("alerta");
        alert.setHeaderText("Ocurrió un error");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }


    @FXML //Este es para que se regrese al menu-view.fxml al picarle
    private void Cancel(){

    }
}
