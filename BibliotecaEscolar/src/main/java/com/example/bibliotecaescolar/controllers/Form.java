package com.example.bibliotecaescolar.controllers;

import com.example.bibliotecaescolar.repository.BookRepo;
import com.example.bibliotecaescolar.model.Book;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
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
    private void Save()throws IOException {
       if (!validate()) return;

       List<String> data = Stream.of(txtISBN,txtAuthor,txtYear,txtTitle,txtEditorial).map(f ->f.getText().trim()).toList();
       String gender = comboGender.getValue();
       String available = comboAvailable.getValue();

       Book book = new Book(data.get(0),data.get(1),data.get(2),data.get(3),data.get(4),available,gender);

       repo.appendLine(book.toCsvLine());
       clear();

    }

    private boolean validate(){
        try {
            boolean fieldVoid = List.of(txtTitle, txtAuthor, txtEditorial,txtYear,txtISBN).stream().anyMatch(f -> f.getText().trim().isEmpty());

            boolean comboGendervoid = List.of(comboGender, comboAvailable).stream().anyMatch(c -> c.getValue()== null || c.getValue().trim().isEmpty());

            int yearInt;
            try {
                yearInt = Integer.parseInt(txtYear.getText());
            }catch (NumberFormatException e ){
                throw new IllegalArgumentException("campo de año no numerico");
            }

            boolean authorTitleCorrect = txtTitle.getText().length() >= 3 && txtAuthor.getText().length() >= 3;
            if (fieldVoid || comboGendervoid) {
                throw new IllegalArgumentException("Campos vacios");
            }
            if (!authorTitleCorrect) {
                throw new IllegalArgumentException("parametros fuera de rango");
            }
            if(yearInt<=1500){
                throw new IllegalArgumentException("parametro de año fuera de rango");
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

    public void clear(){
        txtTitle.clear();
        txtAuthor.clear();
        txtEditorial.clear();
        txtYear.clear();
        txtISBN.clear();
        comboGender.getSelectionModel().clearSelection();
        comboAvailable.getSelectionModel().clearSelection();
    }


    @FXML //Este es para que se regrese al menu-view.fxml al picarle
    private void Cancel() throws IOException{
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("/com/example/bibliotecaescolar/views/menu-view.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) txtYear.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
