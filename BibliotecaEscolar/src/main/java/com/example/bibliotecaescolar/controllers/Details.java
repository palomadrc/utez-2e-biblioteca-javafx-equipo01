package com.example.bibliotecaescolar.controllers;

import com.example.bibliotecaescolar.HelloApplication;
import com.example.bibliotecaescolar.model.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public class Details {
    @FXML private Label lblISBN;
    @FXML private Label lblTitle;
    @FXML private Label lblAuthor;
    @FXML private Label lblYear;
    @FXML private Label lblEditorial;

    @FXML private TextField txtID;
    @FXML private TextField txtISBN;
    @FXML private TextField txtTitle;
    @FXML private TextField txtAuthor;
    @FXML private TextField txtYear;
    @FXML private TextField txtEditorial;
    @FXML private Button btnEdit;
    @FXML private TextField txtAvailable;
    @FXML private TextField txtGender;

    private Book book;
    private boolean editActive=false;


    public void initData(Book book){

        this.book=book;
        txtID.setEditable(false);
        txtID.setText(String.valueOf(book.getId()));
        txtAuthor.setText(book.getauthor());
        txtEditorial.setText(book.getEditorial());
        txtYear.setText(book.getAnio());
        txtISBN.setText(book.getISBN());
        txtTitle.setText(book.getTitle());
        txtAvailable.setText(book.getAvailable());
        txtGender.setText(book.getGender());

        for (TextField f : List.of(txtISBN, txtTitle, txtAuthor, txtYear, txtEditorial, txtID,txtAvailable,txtGender)) {
            f.setEditable(false);
        }

        btnEdit.setOnAction(event ->  {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/bibliotecaescolar/views/form-view.fxml"));
                Parent root =  fxmlLoader.load();
                Form form = fxmlLoader.getController();
                form.initdata(book);
                Stage stage = (Stage) txtTitle.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            }catch (Exception e) {
                e.printStackTrace();
            }
        });


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
