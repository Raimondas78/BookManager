package com.example.book.payload.response;

import com.example.book.entity.Book;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class AllBookResponse {

    public AllBookResponse() {
    }

    public static AllBookResponse fromBook(Book book) {
        AllBookResponse allBookResponse = null;
        String[] arr = book.toString().split("\\|");
        int lastMember;

        if (arr.length == 5) {
            allBookResponse = new BookResponse(book.getName(), book.getAuthor(),
                    book.getBarcode(), book.getQuantity(), book.getPrice());
        } else if (arr.length == 6) {
            lastMember = Integer.parseInt(arr[arr.length - 1]);
            if (lastMember > 10 && lastMember < 1900) {
                allBookResponse = new AntiqueBookResponse(book.getName(), book.getAuthor(),
                        book.getBarcode(), book.getQuantity(), book.getPrice(), lastMember);
            } else if (lastMember <= 10 && lastMember >= 1)
                allBookResponse = new ScienceJournalResponse(book.getName(), book.getAuthor(),
                        book.getBarcode(), book.getQuantity(), book.getPrice(), lastMember);
        }

        return allBookResponse;
    }

    public double getTotalPrice(Book book) {
        String[] arr = book.toString().split("\\|");
        int lastMember = 0;
        double totalPrice = book.getPrice() * book.getQuantity();
        if (arr.length == 6) {
            lastMember = Integer.parseInt(arr[arr.length - 1]);
        }
        if (lastMember < 1900 && lastMember > 10) {
            totalPrice = totalPrice * (LocalDate.now().getYear() - lastMember) / 10;

        }
        if (lastMember <= 10 && lastMember >= 1) {
            totalPrice = totalPrice * lastMember;

        }

        BigDecimal bd = BigDecimal.valueOf(totalPrice);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        totalPrice = bd.doubleValue();
        return totalPrice;
    }

}
