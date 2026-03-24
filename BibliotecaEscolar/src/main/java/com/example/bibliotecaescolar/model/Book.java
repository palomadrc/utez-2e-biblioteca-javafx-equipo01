package com.example.bibliotecaescolar.model;

import javafx.beans.property.*;

public class Book {

    private final IntegerProperty id;
    private final StringProperty ISBN;
    private final StringProperty author;
    private final StringProperty anio;
    private final StringProperty title;
    private final StringProperty editorial;
    private final StringProperty available;
    private final StringProperty gender;

    public Book(int id,String ISBN,String author,String anio,String title,String editorial,String available,String gender) {
        this.id = new SimpleIntegerProperty(id);
        this.ISBN =new SimpleStringProperty(ISBN);
        this.author =new SimpleStringProperty(author);
        this.anio =new SimpleStringProperty(anio);
        this.title =new SimpleStringProperty(title);
        this.editorial =new SimpleStringProperty(editorial);
        this.available =new SimpleStringProperty(available);
        this.gender =new SimpleStringProperty(gender);
    }

    public int getId() {return id.get();}
    public IntegerProperty idProperty() {return id;}

    public String getISBN() {return ISBN.get();}
    public StringProperty ISBNProperty() {return ISBN;}
    public void setISBN(String ISBN){this.ISBN.set(ISBN);}

    public String getauthor() {return author.get();}
    public StringProperty authorProperty() {return author;}
    public void setAuthor(String author){this.author.set(author);}


    public String getAnio() {return anio.get();}
    public StringProperty anioProperty() {return anio;}
    public void setAnio(String anio){this.anio.set(anio);}

}
