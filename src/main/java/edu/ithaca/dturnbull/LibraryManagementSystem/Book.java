package edu.ithaca.dturnbull.LibraryManagementSystem;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;


import java.util.Calendar;


public class Book {

    public String title;
    public String author;
    public String isbn;
    public String publicationDate;
    public String genre;
    public double cost;


    public Book(String title, String author, String publicationDate,String isbn, String genre, double cost) throws UnrecognizedException{
        if((!iswordValid(author) && (!isdateValid(publicationDate)) && !isGenreValid(genre) && !isAmountValid(cost))){
            this.title = title;
            this.author = author;
            this.isbn = isbn;
            this.publicationDate = publicationDate;
            this.genre = genre;
            this.cost = cost;

        }
        else{
            throw new UnrecognizedException("Unkown Text found, please contact the librarian");

        }

    }

    public static boolean iswordValid(String author){
        if(author.isEmpty()){
            return false;
        }
        // if(author.contains("0123456789")){
        //     return false;
        // }
    
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
    
    // add for negative numbers
    
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

    static String getDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("DD-MM-YYYY");
        LocalDateTime now = LocalDateTime.now();
       return dtf.format(now);
    }

    public static boolean isGenreValid(String genre) throws UnrecognizedException{

        if(genre.isEmpty()){
            return false;
        }
        else if(genre.matches("Crime") || genre.matches("Horror")|| genre.matches("Fantasy") || genre.matches("Adventure") || genre.matches("Science fiction") || genre.matches("Romance")){
            return true;
        }
        else{
            throw new UnrecognizedException("Please Meet the Librarian for further assistance");
        }


    }

    //add this to librarian
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


    //add this to kiosk
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
                if (lineFromFile.contains(name)) { // Book found in list
                    System.out.println("Book Found " + name);
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println(" Cannot write to Given List,  " + file.toString());
        }

    }

}



