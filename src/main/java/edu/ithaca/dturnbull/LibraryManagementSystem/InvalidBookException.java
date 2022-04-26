package edu.ithaca.dturnbull.LibraryManagementSystem;

public class InvalidBookException extends Exception{
    private static final long serialVersionUID = 1L;

    public InvalidBookException(String s) {
        super(s);
    }
}
