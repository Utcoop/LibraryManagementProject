package edu.ithaca.dturnbull.LibraryManagementSystem;

public class InvalidUserException extends Exception{
    private static final long serialVersionUID = 1L;

    public InvalidUserException(String s) {
        super(s);
    }
}
