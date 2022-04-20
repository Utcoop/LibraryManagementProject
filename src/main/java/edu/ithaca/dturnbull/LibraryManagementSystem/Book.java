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
    public String publicationDate;
    public String genre;
    public double cost;
    public int copies;


    public Book(String title, String author, String publicationDate, String genre, double cost, int copies) throws UnrecognizedException{
        //!iswordValid(author) && (!isdateValid(publicationDate)) && !isGenreValid(genre) && !isAmountValid(cost))
        if(iswordValid(author) && (isdateValid(publicationDate)) && isGenreValid(genre) && isAmountValid(cost) && (copies > 0)){
            this.title = title;
            this.author = author;
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
        this.copies = 1;
    }

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

    public String getTitle() {
        return title;
    }

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

    public static void main(String[] args) throws UnrecognizedException {
        Book book1 = new Book("Harry Potter","Kenny Sun","02-12-2000","Crime",30.0,1);


        Book book2 = new Book("Percy Jackson","Maddison Beer","02-12-2000","Crime",20.0,5);


        Book book3 = new Book("OMG","Kevin Tao","02-12-2000","Crime",15.0,10);
        
        System.out.println(book1.author);
        System.out.println(book2.author);
        System.out.println(book3.author);
        }
}




