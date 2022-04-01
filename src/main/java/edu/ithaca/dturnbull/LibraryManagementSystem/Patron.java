package edu.ithaca.dturnbull.LibraryManagementSystem;

public class Patron {
    int id;
    String password;
    Double fines;
    Book[] booksOut;
    int maxBooks = 5;


    public Patron(int accountID, String password){
        id = accountID;
        this.password = password;
        fines = 0.0;
        booksOut = new Book[maxBooks];
    }
}