package com.example.book.entity;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class AntiqueBook extends Book {

    private int releaseYear;

    public AntiqueBook(String name, String author, String barcode, int quantity, double price, int releaseYear) {
        super(name, author, barcode, quantity, price);
        if(releaseYear>=1900){
            throw new RuntimeException("Antique book is only the one that has release year earlier than 1900");
        } else{
            this.releaseYear = releaseYear;
        }
    }

    public int tReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

//    public double getTotalPrice(){
//        double totalPrice = getQuantity()*getPrice()*(LocalDate.now().getYear()-this.releaseYear)/10;
//        BigDecimal bd = BigDecimal.valueOf(totalPrice);
//        bd = bd.setScale(2, RoundingMode.HALF_UP);
//        return bd.doubleValue();
//    }

    @Override
    public String toString() {
        return  getName() + "|" +
                getAuthor() + "|" +
                getBarcode() + "|" +
                getQuantity() + "|" +
                getPrice() + "|" + this.releaseYear;
    }
}
