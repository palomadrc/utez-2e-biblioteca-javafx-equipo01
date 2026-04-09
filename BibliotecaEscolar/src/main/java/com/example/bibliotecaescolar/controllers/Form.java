package com.example.bibliotecaescolar.controllers;

import com.example.bibliotecaescolar.repository.BookRepo;
import com.example.bibliotecaescolar.model.Book;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private Book book;

    //Función incializadora para añadir
    public void initialize(){
        comboGender.getItems().addAll("Drama", "Romace", "History", "CiFi", "Literature", "Science", "Fantacy", "Horror");
        comboAvailable.getItems().addAll("Yes", "No");
    }

    //Funcion para recibir libro y modificarlo
    public void initdata(Book book){
        txtAuthor.setText(book.getauthor());
        txtEditorial.setText(book.getEditorial());
        txtYear.setText(book.getAnio());
        txtISBN.setText(book.getISBN());
        txtTitle.setText(book.getTitle());
        comboGender.setValue(book.getGender());
        comboAvailable.setValue(book.getAvailable());

        this.book=book;
    }

    @FXML //guardar el libro y tambien editar (las dos validaciones)
    private void Save()throws IOException {
       if (!validate()) return;
       List<String> data = Stream.of(txtISBN, txtAuthor, txtYear, txtTitle, txtEditorial).map(f -> f.getText().trim()).toList();
       String gender = comboGender.getValue();
       String available = comboAvailable.getValue();
       if(book==null) {
           book = new Book(data.get(0), data.get(1), data.get(2), data.get(3), data.get(4), available, gender);

           repo.appendLine(book.toCsvLine());
           clear();
           Main.showSuccess("Creado con exitooo");
       }
       else {
           book.setISBN(data.get(0));
           book.setAuthor(data.get(1));
           book.setAnio(data.get(2));
           book.setTitle(data.get(3));
           book.setEditorial(data.get(4));
           book.setAvailable(available);
           book.setGender(gender);
           repo.update(book);
           clear();
           Main.showSuccess("Modificado con exito");

       }

    }

    //Validar que todos los campos sean ingresados correctamente
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

            boolean minimalPairs = List.of(txtTitle, txtAuthor, txtEditorial,txtYear,txtISBN).stream().anyMatch(f -> f.getText().trim().length() < 3);
            if (fieldVoid || comboGendervoid) {
                throw new IllegalArgumentException("Campos vacios minimo 3 caracteres");
            }
            if (minimalPairs) {
                throw new IllegalArgumentException("parametros fuera de rango");
            }
            if(yearInt<1500){
                throw new IllegalArgumentException("parametro de año fuera de rango");
            }
            return true;
        }catch (IllegalArgumentException e){
            Main.showMenssage(e);
            return false;
        }
    }

    public void clear(){
        txtTitle.clear();
        txtAuthor.clear();
        txtEditorial.clear();
        txtYear.clear();
        txtISBN.clear();
        comboGender.getSelectionModel().clearSelection();
        comboAvailable.getSelectionModel().clearSelection();
        book=null;
    }


    @FXML //Este es para que se regrese al menu-view.fxml al picarle
    private void cancel() throws IOException{
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("/com/example/bibliotecaescolar/views/menu-view.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) txtYear.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
