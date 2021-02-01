package com.example.book.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String author;
    private String barcode;
    private int quantity;
    private double price;

    public Book(){
    }
    public Book(String name, String author, String barcode, int quantity, double price) {


        this.name = name;
        this.author = author;
        this.barcode = barcode;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalPrice(){
        double totalPrice = getQuantity()*getPrice();
        BigDecimal bd = BigDecimal.valueOf(totalPrice);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    @Override
    public String toString() {
        return name + "|" +
                author + "|" +
                barcode + "|" +
                quantity + "|" + price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return getBarcode().equals(book.getBarcode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBarcode());
    }
}
