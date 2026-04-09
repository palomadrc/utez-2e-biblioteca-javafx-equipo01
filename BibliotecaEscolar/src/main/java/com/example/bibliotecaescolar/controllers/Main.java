package com.example.bibliotecaescolar.controllers;

import com.example.bibliotecaescolar.HelloApplication;
import com.example.bibliotecaescolar.model.Book;
import com.example.bibliotecaescolar.repository.BookRepo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {

    @FXML private TableView<Book> TableView;
    @FXML private TableColumn<Book, Number> colID;
    @FXML private TableColumn<Book, String> colTitle;
    @FXML private TableColumn<Book, String> colAuthor;
    @FXML private TableColumn<Book, String> colYear;
    @FXML private TableColumn<Book, String> colGender;
    @FXML private TableColumn<Book, String> colAvailable;

    @FXML private TextField txtBuscar;

    private final ObservableList<Book> books = FXCollections.observableArrayList();

    private final BookRepo repo = new BookRepo();
    private Form form= new Form();

    @FXML
    private void initialize(){
        configureColumns();
        loadData();
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
    private void generateReport()throws IOException {
        try {
            String date = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            List<Book> books = loadAll();

            StringBuilder format = new StringBuilder();

            format.append("======================Reporte de libros======================\n\n");

            for (Book b : books) {
                format.append("ID:    ").append(b.getId()).append("\n");
                format.append("Title:    ").append(b.getTitle()).append("\n");
                format.append("Year:     ").append(b.getAnio()).append("\n");
                format.append("Author:    ").append(b.getauthor()).append("\n");
                format.append("ISBN:    ").append(b.getISBN()).append("\n");
                format.append("Editorial:    ").append(b.getEditorial()).append("\n");
                format.append("Gender:    ").append(b.getGender()).append("\n");
                format.append("Available:    ").append(b.getAvailable()).append("\n");

                format.append("========================================================\n");

            }

            Path reportPath = Paths.get(
                    System.getProperty("user.home"), "Downloads", "report"+date+".txt"
            );

            Files.writeString(reportPath, format.toString(), StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (Exception e) {
            showMenssage(new IllegalArgumentException("error al generar archivos"));
        }

    }

    public void configureColumns(){
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("anio"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colAvailable.setCellValueFactory(new PropertyValueFactory<>("available"));

        TableView.setEditable(false);
    }

    private List<Book> loadAll() throws IOException{
        List<String> lines = repo.readAllLines();
        ArrayList<Book> copyBook = new ArrayList<>();
        for(String line:lines){
            try {
                Book b = Book.fromCsvLine(line);
                copyBook.add(b);
            }catch (Exception e){

            }
        }
        return  copyBook;

    }

    private void loadData(){
        try {
            List<Book> register = loadAll();
            books.setAll(register);
            TableView.setItems(books);
        } catch (IOException e) {
            showMenssage(new IllegalArgumentException("error al cargar los libros"));
        }
    }

    public static void showMenssage(IllegalArgumentException e){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("alerta");
        alert.setHeaderText("Ocurrió un error");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }


    @FXML
    private void buscar()throws IOException{
        try {
            int searchId = Integer.parseInt(txtBuscar.getText());

            List<Book> books = loadAll();

            for (Book b : books) {
                if (b.getId() == searchId) {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/bibliotecaescolar/views/details-view.fxml"));
                        Parent root = fxmlLoader.load();
                        Details details = fxmlLoader.getController();
                        details.initData(b);
                        Stage stage = (Stage) TableView.getScene().getWindow();
                        stage.setScene(new Scene(root));
                        stage.show();
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else {
                    txtBuscar.setText("id no encontrado");
                }
            }
        }catch (NumberFormatException e){
            txtBuscar.setText("introduce digitos numericos");
        }
    }

    @FXML
    private void eliminar(){
        Book selected = TableView.getSelectionModel().getSelectedItem();
        if(selected == null){
            showMenssage(new IllegalArgumentException("selecciona un libro"));
            return;
        }
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Estas seguro de esta accion??'");
        confirmation.setHeaderText("Eliminar libro");
        confirmation.setContentText("Seguro que quiers Eliminar");
        Optional<ButtonType> result = confirmation.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            int index= books.indexOf(selected);
            books.remove(index);
            try {
                List<String> lines = books.stream().map(Book::toCsvLine).toList();
                repo.saveAll(lines);
            } catch (IOException e) {
                showMenssage(new IllegalArgumentException("Error al borrar"));
            }

        } else {

        }

    }

}
