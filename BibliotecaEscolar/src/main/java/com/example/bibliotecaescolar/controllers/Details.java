package com.example.bibliotecaescolar.controllers;

import com.example.bibliotecaescolar.model.Book;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Details {

    @FXML private Label lblISBN;
    @FXML private Label lblTitle;
    @FXML private Label lblAuthor;
    @FXML private Label lblYear;
    @FXML private Label lblEditorial;
    @FXML private Label lblGender;
    @FXML private Label lblAvailable;

    private Book book;

    //Para que se vean los datos del libro
    public void setBook(Book book) {

    }

    @FXML //este regresa al menu-view
    private void regresar(){

    }

}
