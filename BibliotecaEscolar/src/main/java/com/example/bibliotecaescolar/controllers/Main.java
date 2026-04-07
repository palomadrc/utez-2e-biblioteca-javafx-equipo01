package com.example.bibliotecaescolar.controllers;

import com.example.bibliotecaescolar.HelloApplication;
import com.example.bibliotecaescolar.model.Book;
import com.example.bibliotecaescolar.repository.BookRepo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    private Form form= new Form();

    @FXML
    private void initialize(){

    }

    @FXML //Para ir al form
    private void goToForm(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/bibliotecaescolar/views/form-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            Stage stage = (Stage) TableView.getScene().getWindow();

            stage.setTitle("Formulario");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
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
