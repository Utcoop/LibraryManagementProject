package edu.ithaca.dturnbull.LibraryManagementSystem;

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
        assertThrows(UnrecognizedException.class, ()-> new Book( "Jumanji", "Lord","24-01-2015", "Horror", 1234.333));



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
