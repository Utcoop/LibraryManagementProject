package edu.ithaca.dturnbull.LibraryManagementSystem;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class bookMethods {
    private Scanner input = new Scanner(System.in);
    
    String getDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
       return dtf.format(now);
    }




    public void addBooktoRecords(){
        Books book1 = new Books();
        String fileName = "list.txt";
        String content = "Accessible only to Librarian\n" +
        "Date:- " + getDate() + "\n\n" + book1;
        File file = new File(fileName);
        try {
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteRecord(){
        String fileName = "list.txt";
        File file = new File(fileName);
        file.delete();
    }

   

    class Books{
        public String title;
        public String author;
        public String publicationDate;
        public String genre;
        public double cost;
        public int numOfCopies;
        public int numCheckedOut;
        


        public Books(String title, String author, String publicationDate, String genre, double cost, int numOfCopies, int numCheckedOut){
            this.title = title;
            this.author = author;
            this.publicationDate = publicationDate;
            this.genre = genre;
            this.cost = cost;
            this.numOfCopies = numOfCopies;
            this.numCheckedOut = numCheckedOut;
           
        }

        public Books(){
            System.out.print("Enter Title:");
            this.title = input.nextLine();
            
            System.out.print("Enter Author:");
            this.author = input.nextLine();
            
            System.out.print("Enter Publication date:");
            this.publicationDate = input.nextLine();
            
            System.out.print("Enter Genre:");
            this.genre = input.nextLine();
            
            System.out.print("Enter cost:");
            this.cost = input.nextDouble();

            System.out.print("Enter number of available copies:");
            this.numOfCopies= input.nextInt();
            
            System.out.print("Enter number of checked out copies:");
            this.numCheckedOut= input.nextInt();
            
        }
    
        public String getTitle() {
            return title;
        }
    
        public String getauthor() {
            return author;
        }

        public String getPublicationDate(){
            return publicationDate;
        }

        public String getGenre() {
            return genre;
        }
        public double getCost() {
            return cost;
        }
    
        public int getNumOfCopies() {
            return numOfCopies;
        }

        public int getCheckedOut() {
            return numCheckedOut;
        }
    
        @Override
        public String toString() {
            return  title + "," + author +  "," + publicationDate+  "," + genre+ "," + cost + "," + numOfCopies + "," + numCheckedOut;
    
        }


    }

    
}
