package edu.ithaca.dturnbull.LibraryManagementSystem;

import java.util.LinkedList;
import java.util.List;

public class Patron {
    int Id;
    String password;
    String name;
    Double fines;
    List<Book> booksOut;
    int maxBooks = 5;
    List<Book> wishlist;


    public Patron(Library library, String name, String password){
        Id = library.getNextPatronId();
        library.increaseNextPatronId();
        this.password = password;
        this.name = name;
        fines = 0.0;
        booksOut = new LinkedList<>();
        wishlist = new LinkedList<>();
    }


    /**
     * 
     * @param book book to add to wishlist
     */
    public void addToWishlist( Book book){
        //TODO
    }

    /**
     * 
     * @param book book to remove from wishlist
     */
    public void removeFromWishlist(Book book){
        //TODO
    }

    /**
     * @return the fines that  are outstanding in this account
     */
    public double checkFines(){
        //TODO
        return -1;
    }

    /**
     * @return a string that represents the current wishlist
     */
    public String checkWishlist(){
        //TODO
        return "";
    }

    /**
     * 
     * @param book
     * @return True if the book was checked out successfully and false otherwise
     */
    public Boolean bookCheckout(Book book){
        //TODO
        return false;
    }

    public int getId(){
        return Id;
    }

    public String getName(){
        return name;
    }

    public String getPassword(){
        return password;
    }
}