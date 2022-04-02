package edu.ithaca.dturnbull.LibraryManagementSystem;

import java.util.ArrayList;
import java.util.List;

public class Librarian {
    protected static int id;
    protected static Library library;

    public Librarian(Library library) {
        this.library = library;
    }

    /***
     * 
     * @param accountId of the patron
     * @param password of the patron
     * @post the credential of the patron is confirmed
     */
    public boolean confirmCred(int accountId, String password) {
        List<Patron> patrons = new ArrayList<>(library.getPatrons());
        for (int i = 0; i < patrons.size(); i++) {
            if (patrons.get(i).getId() == accountId) {
            }
        }
        return false;
    }

    /***
     * 
     */
    public void checkBalance(String patronId) {

    }

    public void payFine(double amount) {

    }

    public void checkBook(String title) {

    }

    public void borrowBook(String title) {

    }

    public void returnBook(String title) {

    }

    public void addToWishList(String title) {

    }

    public void removeFromWishList(String title) {

    }

    public void calculateFine() {

    }

    public void checkWishList() {

    }

    public int getId() {
        return id;
    }
}
