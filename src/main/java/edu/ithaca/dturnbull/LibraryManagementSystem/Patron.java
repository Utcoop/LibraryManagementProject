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
     * @param id user attempts to login in with this id
     * @param password user attempts to login with this password
     * @return true if the combination is true and false otherwise 
     */
    public Boolean login(int Id, String password){
        return ( this.Id== Id && this.password.equals(password));
    }
    /**
     * 
     * @param book book to add to wishlist
     */
    public void addToWishlist(Book book){
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
    }

    /**
     * @return a string that represents the current wishlist
     */
    public String checkWishlist(){
        //TODO
    }

    /**
     * 
     * @param book
     * @return True if the book was checked out successfully and false otherwise
     */
    public Boolean bookCheckout(Book book){
        //TODO
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