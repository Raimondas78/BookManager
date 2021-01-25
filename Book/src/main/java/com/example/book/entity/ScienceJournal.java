package com.example.book.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ScienceJournal extends Book {

    private int scienceIndex;

    public ScienceJournal(){
    }

    public ScienceJournal(String name, String author, String barcode, int quantity, double price, int scienceIndex) {
        super(name, author, barcode, quantity, price);
        if(scienceIndex>10 || scienceIndex<1){
            throw new RuntimeException("Science index must be from the interval 1-10");
        } else {
            this.scienceIndex = scienceIndex;
        }
    }

    public int getScienceIndex() {
        return scienceIndex;
    }

    public void setScienceIndex(int scienceIndex) {
        this.scienceIndex = scienceIndex;
    }

//    public double getTotalPrice(){
//        double totalPrice = getQuantity()*getPrice()*this.scienceIndex;
//        BigDecimal bd = BigDecimal.valueOf(totalPrice);
//        bd = bd.setScale(2, RoundingMode.HALF_UP);
//        return bd.doubleValue();
//    }

    @Override
    public String toString() {
        return getName() + "|" +
                    getAuthor() + "|" +
                    getBarcode() + "|" +
                    getQuantity() + "|" +
                    getPrice() + "|" + this.scienceIndex;
    }
}
