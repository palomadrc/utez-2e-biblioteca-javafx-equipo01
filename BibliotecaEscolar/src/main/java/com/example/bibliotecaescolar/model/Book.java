package com.example.bibliotecaescolar.model;

import javafx.beans.property.*;

public class Book {

    private static int nextId=0;
    private final IntegerProperty id;
    private final StringProperty ISBN;
    private final StringProperty author;
    private final StringProperty anio;
    private final StringProperty title;
    private final StringProperty editorial;
    private final StringProperty available;
    private final StringProperty gender;



    public Book(String ISBN,String author,String anio,String title,String editorial,String available,String gender) {
        this.id = new SimpleIntegerProperty(nextId++);
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

    public String getTitle() {return title.get();}
    public StringProperty titleProperty() {return title;}
    public void setTitle(String title){this.title.set(title);}

    public String getEditorial() { return editorial.get(); }
    public void setEditorial(String editorial) { this.editorial.set(editorial); }
    public StringProperty editorialProperty() { return editorial; }

    public String getAvailable() { return available.get(); }
    public void setAvailable(String available) { this.available.set(available); }
    public StringProperty availableProperty() { return available; }

    public String getGender() { return gender.get(); }
    public void setGender(String gender) { this.gender.set(gender); }
    public StringProperty genderProperty() { return gender; }

    public String toCsvLine(){
        return String.join(",",String.valueOf(getId()),getISBN(),getauthor(),getAnio(),getTitle(),getEditorial(),getAvailable(),getGender());
    }

    public static Book fromCsvLine(String line){
        String[] b = line.split(",",-1);
        if(b.length<8) throw new IllegalArgumentException("Linea invalida");
        Book book = new Book(b[1], b[2], b[3], b[4], b[5], b[6], b[7]);
        book.id.set(Integer.parseInt(b[0]));
        return book;
    }


}


