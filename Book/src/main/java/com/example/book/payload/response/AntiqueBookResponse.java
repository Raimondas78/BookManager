package com.example.book.payload.response;

public class AntiqueBookResponse extends BookResponse {

    private int releaseYear;

    public AntiqueBookResponse(String name, String author, String barcode, int quantity, double price, int releaseYear) {
        super(name, author, barcode, quantity, price);
        this.releaseYear = releaseYear;
    }

    public int getReleaseYear() {
        return releaseYear;
    }



}
