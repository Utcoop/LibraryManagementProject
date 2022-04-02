package edu.ithaca.dturnbull.LibraryManagementSystem;

import java.util.ArrayList;
import java.util.List;

public class Librarian {
    protected static int id;
    protected static Library library;

    /***
     * 
     * @param accountId of the patron
     * @param password of the patron
     * @post the credential of the patron is confirmed
     */
    public void confirmCred(String accountId, String password) {
        ArrayList<Patron> patrons = new ArrayList<>(library.getPatrons());
        for (Patron patron: patrons) {
        
        }
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
