package com.example.bibliotecaescolar.controllers;

import com.example.bibliotecaescolar.model.Book;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Details {

    @FXML private Label lblISBN;
    @FXML private Label lblTitle;
    @FXML private Label lblAuthor;
    @FXML private Label lblYear;
    @FXML private Label lblEditorial;
    @FXML private Label lblGender;
    @FXML private Label lblAvailable;
    @FXML private TextField txtAvailable;


    private Book book;


    @FXML
    private void initialize(){
        for(texfield f:)
    }


    //Para que se vean los datos del libro
    public void setBook(Book book) {

    }

    public void initData(Book book){
        this.book=book;
    }

    @FXML //este regresa al menu-view
    private void cancel() throws IOException {
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("/com/example/bibliotecaescolar/views/menu-view.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) lblAuthor.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
