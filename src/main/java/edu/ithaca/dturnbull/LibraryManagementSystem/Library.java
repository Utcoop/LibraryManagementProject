package edu.ithaca.dturnbull.LibraryManagementSystem;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private static List<Librarian> librarians;
    private static List<Patron> patrons; 
    private static List<Book> books;
    private static int nextLibrarianId = 0;
    private static int nextPatronId = 0;

    public Library() {
        librarians = new ArrayList<>();
        patrons = new ArrayList<>();
        books = new ArrayList<>();
    }

    public void increaseNextLibrarianId() {
        nextLibrarianId++;
    }

    public int getNextLibrarianId() {
        return nextLibrarianId;
    }
}
