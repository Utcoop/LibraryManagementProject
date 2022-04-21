package edu.ithaca.dturnbull.LibraryManagementSystem;

public class Patron {
    int Id;
    String password;
    String name;
    Double fines;
    Book[] booksOut;
    int maxBooks = 5;
    Book[] wishlist;


    public Patron(Library library, String name, String password){
        Id = library.getNextPatronId();
        library.increaseNextPatronId();
        this.password = password;
        this.name = name;
        fines = 0.0;
        booksOut = new Book[maxBooks];
        wishlist = new Book[maxBooks];
    }


    /**
     * 
     * @param book book to add to wishlist
     */
    public void addToWishlist( book){
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