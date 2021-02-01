package com.example.book.repository;

import com.example.book.entity.AntiqueBook;
import com.example.book.entity.Book;
import com.example.book.entity.ScienceJournal;
import com.example.book.payload.request.BookUpdateRequest;
import org.springframework.stereotype.Repository;
import java.io.*;
import java.util.*;


@Repository
public class BookRepository{


    public void saveBook(Book book){
        File file = new File("books.txt");

        try {
            FileWriter fileWriter = new FileWriter(file, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            List<Book> readBooks = new ArrayList<>(getAllBooksFromTheFile());
            System.out.println("This is list print");
            readBooks.forEach(System.out::println);
            if(readBooks.isEmpty()){
                String stringToWrite = book.toString();
                printWriter.println(stringToWrite);
            }
            else if(readBooks.contains(book)){
                throw new RuntimeException("Such book already exists");
            } else{
                String stringToWrite = book.toString();
                printWriter.println(stringToWrite);
            }
            printWriter.flush();
            printWriter.close();
            System.out.println("Serialized data is saved");

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public  List<Book> getAllBooksFromTheFile(){
        File file = new File("books.txt");

        List<Book> books = new ArrayList<>();
        try( Scanner scanner = new Scanner(file)){
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] item = line.split("\\|");
                    String name = item[0];
                    String author = item[1];
                    String barcode = item[2];
                    int quantity = Integer.parseInt(item[3]);
                    double price = Double.parseDouble(item[4]);
                if(item.length==5) {
                    books.add(new Book(name, author, barcode, quantity, price));
                } else if(item.length ==6){
                    int sciIndexOrReleaseYear = Integer.parseInt(item[5]);
                    if(sciIndexOrReleaseYear<1900 && sciIndexOrReleaseYear >10) {
                        books.add(new AntiqueBook(name, author, barcode, quantity, price, sciIndexOrReleaseYear));
                    } else if(sciIndexOrReleaseYear<=10 && sciIndexOrReleaseYear>1){
                        books.add(new ScienceJournal(name, author, barcode, quantity, price, sciIndexOrReleaseYear));
                    }
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return books;
    }

    public Optional<Book> findByBarcode(String barcode) {

        return getAllBooksFromTheFile().
                stream().
                filter(b -> b.getBarcode().equals(barcode)).findAny();
    }

    public void updateBook(Book book, BookUpdateRequest bookUpdateRequest) {
        File file = new File("books.txt");

        Book newBook = null;
        String barcode = bookUpdateRequest.getBarcode();
        String name = book.getName();
        String author = book.getAuthor();
        double price = book.getPrice();
        int quantity = book.getQuantity();

        if (bookUpdateRequest.getName() != null) {
            name = bookUpdateRequest.getName();
        }
        if (bookUpdateRequest.getAuthor() != null) {
            author = bookUpdateRequest.getAuthor();
        }
        if (bookUpdateRequest.getPrice() != 0 && bookUpdateRequest.getPrice() >0) {
            price = bookUpdateRequest.getPrice();
        }
        if (bookUpdateRequest.getQuantity() != 0 && bookUpdateRequest.getQuantity()>=0) {
            quantity = bookUpdateRequest.getQuantity();
        }
        int lastMember;
        String[] arr = book.toString().split("\\|");
        switch(arr.length){
            case 5:
                newBook = new Book(name,author, barcode, quantity, price);
                break;
            case 6:
                if (bookUpdateRequest.getLastMember() != 0 && bookUpdateRequest.getLastMember()>0) {
                    lastMember = bookUpdateRequest.getLastMember();
                    if (lastMember > 10 && lastMember < 1900) {
                        newBook = new AntiqueBook(name, author, barcode, quantity, price, lastMember);
                    } else {
                        newBook = new ScienceJournal(name, author, barcode, quantity, price, lastMember);
                    }
                    break;
                }
        }

        List<Book> bookListFromFile = new ArrayList<>(getAllBooksFromTheFile());
        int index = bookListFromFile.indexOf(book);
        bookListFromFile.set(index, newBook);
        file.delete();
        File newFile = new File("books.txt");

        try(FileWriter fileWriter = new FileWriter(newFile,true);
            PrintWriter printWriter = new PrintWriter(fileWriter)) {
            for(Book b: bookListFromFile){
                printWriter.println(b.toString());
            }
        }  catch (IOException e){
        e.printStackTrace();
    }

    }
}

