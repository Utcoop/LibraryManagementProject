package edu.ithaca.dturnbull.LibraryManagementSystem;


import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

import java.util.Calendar;

/**
 * Book
 * This class will allow to check the entered information about books
 * @author Vaibhav Zaveri
 * 4/21/2022
 */


public class Book {

    public String title;
    public String author;
    public String isbn;
    public String publicationDate;
    public String genre;
    public double cost;
    public int copies;
    public String checkOutDate;
    public String dueDate;
    public Boolean penalized;

    /**
     * constructor
     * @param title
     * @param author
     * @param publicationDate
     * @param isbn
     * @param genre
     * @param cost
     * @throws UnrecognizedException
     */


    public Book(String title, String author, String isbn,String publicationDate, String genre, double cost, int copies) throws UnrecognizedException{
        //!iswordValid(author) && (!isdateValid(publicationDate)) && !isGenreValid(genre) && !isAmountValid(cost))
        if(iswordValid(author) && (isdateValid(publicationDate)) && isGenreValid(genre) && isAmountValid(cost) && (copies > 0)){
            this.title = title;
            this.author = author;
            this.isbn = isbn;
            this.publicationDate = publicationDate;
            this.genre = genre;
            this.cost = cost;
            this.copies = copies;
        }
        else{
            throw new UnrecognizedException("Unkown Text found, please contact the librarian");

        }

    }
  

    public Book(Book book) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String checkOutDate = formatter.format(date);
        Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 7);
		String dueDate = formatter.format(calendar.getTime());

        this.title = book.title;
        this.author = book.author;
        this.publicationDate = book.publicationDate;
        this.genre = book.genre;
        this.cost = book.cost;
        this.copies = 1;
        this.checkOutDate = checkOutDate;
        this.dueDate = dueDate;
        this.penalized = false;
    }

    /**
     * iswordValid()
     * checks if entered word is valid
     * @param author
     * @return
     * method type - accessor - checks word
     */
    public static boolean iswordValid(String author){
        if(author == ""){
            return false;
        }
        char[] charArray = author.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
           char ch = charArray[i];
           if ((!(ch >= 'a' && ch <= 'z') ||(!(ch >= 'A' && ch <= 'Z')))&& (!author.contains(" "))) {
              return false;
           }
           else if(ch>'0' && ch <'9'){
               return false;
           }
        }
        return true;
     

    }
    
    /**
     * isIsbnValid()
     * checks if entered isbn is valid
     * @param isbn
     * @return
     * @throws IllegalArgumentException
     * method type - accessor - checks isbn
     */
    
    public static boolean isIsbnValid(String isbn) throws IllegalArgumentException{
        if(isbn == null){
            return false;
        }
        else if(isbn.length()> 13|| isbn.length() <13){
            throw new IllegalArgumentException("Please check the entered isbn number");

        }
        char[] charArray = isbn.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char ch = charArray[i];
            if(ch>'0' && ch <'9'){
                return true;
            }
            else{
                return false;
            }
        }
        
        return true;
    

    }

    /**
     * isdateValid()
     * checks if entered publication date is valid
     * @param publicationDate
     * @return
     * @throws IllegalArgumentException
     * method type - accessor - checks date
     */


    public static boolean isdateValid(String publicationDate) throws IllegalArgumentException{
        String currentDate = getDate();
        SimpleDateFormat myFormat = new SimpleDateFormat("DD-MM-YYYY");

        try {

            Date pubDate = myFormat.parse(publicationDate);
            Date current = myFormat.parse(currentDate);
            long difference = current.getTime() - pubDate.getTime();
            float daysDifference = (difference / (1000*60*60*24));
            if(daysDifference < 1){
                return false;
            }
            
        }
        catch (Exception e) {
        }
        if(publicationDate.isEmpty()){
            return false;
        }
       return true;
        

    }

    /**
     * getDate()
     * used to get current date
     * @return date in specified format
     */

    static String getDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("DD-MM-YYYY");
        LocalDateTime now = LocalDateTime.now();
       return dtf.format(now);
    }

    /**
     * isGenreValid()
     * checks if genre is available or not
     * @param genre
     * @throws UnrecognizedException if genre is invalid
     * method type - accessor - checks genre
     */
    public static boolean isGenreValid(String genre) throws UnrecognizedException{

        if(genre == ""){
            return false;
        }
        else if(genre.matches("Crime") || genre.matches("Horror")|| genre.matches("Fantasy") || genre.matches("Adventure") || genre.matches("Science fiction") || genre.matches("Romance")){
            return true;
        }
        else{
            throw new UnrecognizedException("Please Meet the Librarian for further assistance");
        }


    }

    /**
     * setDate()
     * prints current time and helps trach time after book is checkedOut
     */

    public void setDate(){
        Scanner input = new Scanner(System.in);

        Calendar ct1 = Calendar.getInstance();
        ct1.set(Calendar.MONTH,input.nextInt());
        ct1.set(Calendar.DATE,input.nextInt());
        ct1.set(Calendar.YEAR,input.nextInt());
        Date checkedOutTime = ct1.getTime();
        System.out.println("Date book was checked out" + checkedOutTime);
        checkedOutTime.setTime(1000999);
        System.out.println("Time after the date that the book was checked out" + checkedOutTime);
    }

    /**
     * isAmountValid
     * checks if the entered amount is valid
     * @param amount
     * method type - accessor - checks amount
     */
    public static boolean isAmountValid(double amount){
    String doubleStr = Double.toString(amount);

    if(amount < 0){
        return false;
        
    }
    else if(doubleStr.substring(doubleStr.lastIndexOf('.'), doubleStr.length() - 1).length() > 2){ //check if amount has 5 or more digits (possibility that there is 3 decimals) 300.67 , 30.678
        return false;
    }
    else{
        return true;
    }
    }

    /**
     * bookSearch
     * Used to look for book in a txt file
     * method type:accessor
     */
    public static void bookSearch(){
        File file = new File("BookList.txt");
        Scanner lookup = new Scanner(System.in);
        System.out.println(" enter Title ");
        String name = lookup.next();
        Scanner scanner;
        try {
            scanner = new Scanner(file).useDelimiter( ",");
    
            while (scanner.hasNext()) {
                final String lineFromFile = scanner.nextLine();
                if (lineFromFile.contains(name)) { 
                    System.out.println("Book Found " + name);
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println(" Cannot write to Given List,  " + file.toString());
        }

    }

    /**
     * returns title
     * @return
     * method type:accessor
     */
    public String getTitle() {
        return title;
    }
    /**
     * returns author
     * @return
     * method type:accessor
     */
    public String getAuthor() {
        return author;
    }
    /**
     * 
     * @return
     * method type:accessor
     */
    public String getIsbn() {
        return isbn;
    }
    /**
     * 
     * method type:accessor
     */
    public String getPublicationDate() {
        return publicationDate;
    } 
    /**
     * 
     * @return
     * method type:accessor
     */
    public String getGenre() {
        return genre;
    }
    /**
     * returns cost
     * @return
     * method type:accessor
     */
    public double getCost() {
        return cost;
    }
}




