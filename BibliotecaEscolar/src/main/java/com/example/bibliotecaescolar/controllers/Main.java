package com.example.bibliotecaescolar.controllers;

import com.example.bibliotecaescolar.model.Book;
import com.example.bibliotecaescolar.repository.BookRepo;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class Main {

    @FXML private TableView<Book> TableView;
    @FXML private TableColumn<Book, Number> colID;
    @FXML private TableColumn<Book, Number> colTitle;
    @FXML private TableColumn<Book, Number> colAuthor;
    @FXML private TableColumn<Book, Number> colYear;
    @FXML private TableColumn<Book, Number> colGender;
    @FXML private TableColumn<Book, Number> colAvailable;

    @FXML private TextField txtBuscar;


    private BookRepo repo = new BookRepo();

    @FXML
    private void initialize(){

    }

    @FXML //Para ir al form
    private void goToForm(){

    }

    @FXML
    private void buscar(){

    }

    @FXML
    private void eliminar(){

    }

    @FXML
    private void update(){

    }

    @FXML //este tiene que regresar al form-view
    private void editar(){

    }


}
