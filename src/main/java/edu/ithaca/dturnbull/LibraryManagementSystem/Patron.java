package edu.ithaca.dturnbull.LibraryManagementSystem;

public class Patron {
    int id;
    String name;
    String password;
    Double fines;
    Book[] booksOut;
    int maxBooks = 5;


    public Patron(Library library, String name, String password){
        name = name;
        id = library.getNextPatronId();
        library.increaseNextPatronId();
        this.password = password;
        fines = 0.0;
        booksOut = new Book[maxBooks];
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}