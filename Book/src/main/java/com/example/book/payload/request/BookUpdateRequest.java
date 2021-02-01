package com.example.book.payload.request;


import javax.validation.constraints.NotNull;

public class BookUpdateRequest {

    private String name;
    private String author;
    private String barcode;
    private int quantity;
    private double price;
    private int lastMember;

    public BookUpdateRequest() {
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
