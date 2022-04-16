package edu.ithaca.dturnbull.LibraryManagementSystem;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private static List<Librarian> librarians;
    private static List<Patron> patrons; 
    private static List<Book> books;
    private static List<Patron> reportedPatrons;
    private static int nextLibrarianId = 0;
    private static int nextPatronId = 0;

    public Library() {
        librarians = new ArrayList<>();
        patrons = new ArrayList<>();
        books = new ArrayList<>();
        reportedPatrons = new ArrayList<>();
    }

    //String title, String author, String publicationDate, String genre, double cost
    /***
     * @param title, author, publicationDate, genre, cost of the new book
     * @post the new book is added to the library's book list
     * @throws UnrecognizedException
     */
    public void addBook(String title, String author, String publicationDate, String genre, double cost) throws UnrecognizedException {
        Book newBook = new Book(title, author, publicationDate, genre, cost);
        books.add(newBook);
    } 

    /***
     * @param title of the book to be removed
     * @post the book is removed from the library's book list
     */
    public void removeBook(String title) {
        for (Book book : books) {
            if (book.getTitle() == title) {
                books.remove(book);
            }
        }
    }

    public void increaseNextLibrarianId() {
        nextLibrarianId++;
    }

    public int getNextLibrarianId() {
        return nextLibrarianId;
    }

    public void increaseNextPatronId() {
        nextPatronId++;
    }

    public int getNextPatronId() {
        return nextPatronId;
    }

    public List<Patron> getPatrons() {
        return patrons;
    }

    public List<Patron> getReportedPatrons() {
        return reportedPatrons;
    }

    public void addPatron(Patron patron) {
        patrons.add(patron);
    }

    public void removePatron(Patron patron) {
        patrons.remove(patron);
    }

    public void addReportedPatron(Patron patron) {
        reportedPatrons.add(patron);
    }

    public List<Book> getBooks() {
        return books;
    }
}
