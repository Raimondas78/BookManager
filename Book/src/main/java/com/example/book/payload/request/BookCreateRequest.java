package com.example.book.payload.request;

import com.example.book.entity.AntiqueBook;
import com.example.book.entity.Book;
import com.example.book.entity.ScienceJournal;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BookCreateRequest {

    private final String name;
    private final String author;
    private final String barcode;
    private final int quantity;
    private final double price;
    private final int lastMember;

    @JsonCreator
    public BookCreateRequest(@JsonProperty("name") String name,
                             @JsonProperty("author") String author,
                             @JsonProperty("barcode") String barcode,
                             @JsonProperty("quantity") int quantity,
                             @JsonProperty("price") double price,
                             @JsonProperty("lastMember") int lastMember) {
        this.name = name;
        this.author = author;
        this.barcode = barcode;
        this.quantity = quantity;
        this.price = price;
        this.lastMember = lastMember;
    }

    public Book asBook(BookCreateRequest bookCreateRequest){
        Book asBook;

        System.out.println("This is last member of bookcreaterequest = " + bookCreateRequest.getLastMember());
        if(bookCreateRequest.getLastMember()==0){
            asBook = new Book(bookCreateRequest.getName(), bookCreateRequest.getAuthor(), bookCreateRequest.getBarcode(), bookCreateRequest.getQuantity(), bookCreateRequest.getPrice());
        } else if(bookCreateRequest.getLastMember()>10 && bookCreateRequest.getLastMember()<1900){
            asBook = new AntiqueBook(bookCreateRequest.getName(), bookCreateRequest.getAuthor(), bookCreateRequest.getBarcode(),
                    bookCreateRequest.getQuantity(), bookCreateRequest.getPrice(), bookCreateRequest.getLastMember());
        } else if(bookCreateRequest.getLastMember()<=10 && bookCreateRequest.getLastMember()>=1){
            asBook = new ScienceJournal(bookCreateRequest.getName(), bookCreateRequest.getAuthor(), bookCreateRequest.getBarcode(),
                    bookCreateRequest.getQuantity(), bookCreateRequest.getPrice(), bookCreateRequest.getLastMember());
        } else{
            throw new RuntimeException("Check if all the fields are entered correctly");
        }
        System.out.println("Book from the method asBook = "+asBook);
        return asBook;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getBarcode() {
        return barcode;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public int getLastMember() {
        return lastMember;
    }


}
