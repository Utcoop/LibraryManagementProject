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
}
