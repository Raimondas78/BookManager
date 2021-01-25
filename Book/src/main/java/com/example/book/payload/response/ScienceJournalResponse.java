package com.example.book.payload.response;


public class ScienceJournalResponse extends BookResponse{

    private int scienceIndex;

    public ScienceJournalResponse(String name, String author, String barcode, int quantity, double price, int scienceIndex) {
        super(name, author, barcode, quantity, price);
        this.scienceIndex = scienceIndex;
    }

    public int getScienceIndex() {
        return scienceIndex;
    }

}
