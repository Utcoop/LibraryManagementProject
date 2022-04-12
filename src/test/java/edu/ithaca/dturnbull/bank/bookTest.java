package edu.ithaca.dturnbull.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.ithaca.dturnbull.LibraryManagementSystem.Book;
import edu.ithaca.dturnbull.LibraryManagementSystem.UnrecognizedException;

public class bookTest {

    @Test 
    void constructorTest() throws UnrecognizedException{
        assertThrows(UnrecognizedException.class, ()-> new Book( "Jumanji", "Lord","24-01-2015","1298723678911", "Horror", 1234.333));
        assertThrows(UnrecognizedException.class, ()-> new Book( "", "Lord","24-01-2015","7634521891002", "Horror", 12));
        assertThrows(UnrecognizedException.class, ()-> new Book( "Snakes on the plane", "Lord","24-01-2015","1002200780010", "Derek", 59.99));
        assertThrows(UnrecognizedException.class, ()-> new Book( "Jumanji", "Lord","04-30-2099","2389876512345", "Horror", 1234.333));
        



    }

    @Test
    void isIsbnValidTest() throws IllegalArgumentException{
        assertThrows(IllegalArgumentException.class, ()-> Book.isIsbnValid("1222"));
        assertThrows(IllegalArgumentException.class, ()-> Book.isIsbnValid("0"));
        assertFalse(Book.isIsbnValid("-128988912229"));
        assertTrue(Book.isIsbnValid("1234567891011"));

    }


    @Test
    void iswordValidTest(){
        //True cases
        assertTrue(Book.iswordValid("Mark Twain"));
        assertTrue(Book.iswordValid("Mark hENRY"));
        

        //False cases
        assertFalse(Book.iswordValid(""));
        assertFalse(Book.iswordValid("3245$$$"));
        assertFalse(Book.iswordValid("..Ascs.aaddcc"));
        assertFalse(Book.iswordValid("24 henrey"));

    }

    @Test
    void isdateValidTest(){
        //True cases
        assertTrue(Book.isdateValid("02-12-2000"));

        //False Cases
        assertFalse(Book.isdateValid("01-1-9999"));
        assertFalse(Book.isdateValid("02-33-2099"));
        assertFalse(Book.isdateValid("99-99-9999"));


    }

    @Test
    void isGenreValidTest() throws UnrecognizedException{

        //True cases
        assertTrue(Book.isGenreValid("Horror"));
        assertTrue(Book.isGenreValid("Crime"));



        //False cases
        assertThrows(UnrecognizedException.class,()-> Book.isGenreValid("Action"));
        assertFalse(Book.isGenreValid(""));




    }

    
}
