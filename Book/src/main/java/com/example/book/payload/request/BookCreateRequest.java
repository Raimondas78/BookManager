package com.example.book.payload.request;

import com.example.book.entity.AntiqueBook;
import com.example.book.entity.Book;
import com.example.book.entity.ScienceJournal;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BookCreateRequest {
    @NotBlank(message = "Book name should not be blank")
    private final String name;
    @NotBlank(message = "Book author should not be blank")
    private final String author;
    @NotNull(message = "Barcode should not be null")
    private final String barcode;
    @Min(0)
    private final int quantity;
    @Min(0)
    private final double price;
    @Min(1)
    @Max(1900)
    private final int lastMember;

    @JsonCreator
    public BookCreateRequest(@JsonProperty("name") String name,
                             @JsonProperty("author") String author,
                             @JsonProperty("barcode") String barcode,
                             @JsonProperty("quantity") int quantity,
                             @JsonProperty("price") double price,
                             @JsonProperty("lastMember") int lastMember) {
        if(barcode.isBlank()){
            throw new IllegalArgumentException("barcode can't be null");
        }


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
