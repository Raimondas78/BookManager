package com.example.book.payload.response;

import com.example.book.entity.Book;

public class BookResponse extends AllBookResponse {

    private String name;
    private String author;
    private String barcode;
    private int quantity;
    private double price;

    public BookResponse(String name, String author, String barcode, int quantity, double price) {
        this.name = name;
        this.author = author;
        this.barcode = barcode;
        this.quantity = quantity;
        this.price = price;
    }

    public static BookResponse fromBook(Book book) {
        return new BookResponse(book.getName(), book.getAuthor(),
                book.getBarcode(), book.getQuantity(), book.getPrice());
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


}
