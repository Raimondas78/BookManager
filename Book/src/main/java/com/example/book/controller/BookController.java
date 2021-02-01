package com.example.book.controller;

import com.example.book.entity.Book;
import com.example.book.payload.request.BookCreateRequest;
import com.example.book.payload.request.BookUpdateRequest;
import com.example.book.payload.response.AllBookResponse;
import com.example.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BookController {

    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("books/price/{barcode}")
    public double getPrice(@PathVariable("barcode") String barcode) {
        Optional<Book> book = bookRepository.findByBarcode(barcode);
        if (book.isEmpty()) {
            throw new NoSuchElementException();
        }
        Book newBook = book.get();

        return newBook.getTotalPrice();
                //AllBookResponse.fromBook(newBook).getTotalPrice(newBook);
    }


    @GetMapping("books/{barcode}")
    public AllBookResponse findBook(@PathVariable("barcode") String barcode) {
        Optional<Book> book = bookRepository.findByBarcode(barcode);

        if (book.isEmpty()) {
            throw new NoSuchElementException();
        }
        return AllBookResponse.fromBook(book.get());
    }

    @PostMapping("books")
    public void addBook(@RequestBody BookCreateRequest bookCreateRequest) {
        Book book = bookCreateRequest.asBook(bookCreateRequest);
        bookRepository.saveBook(book);
    }

    @PatchMapping("books/{barcode}")
    public ResponseEntity<Void> updateBook(@PathVariable("barcode") String barcode,
                                           @RequestBody BookUpdateRequest bookUpdateRequest) {
        Optional<Book> bookOptional = bookRepository.findByBarcode(barcode);
        if (bookOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Book book = bookOptional.get();
        bookRepository.updateBook(book, bookUpdateRequest);
        return ResponseEntity.noContent().build();
    }
}
