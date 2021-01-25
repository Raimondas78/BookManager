package com.example.book;

import com.example.book.entity.Book;
import com.example.book.entity.ScienceJournal;
import com.example.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.io.File;
import java.util.NoSuchElementException;


@Component
public class CommandLine implements CommandLineRunner {

    private final BookRepository bookRepository;

    @Autowired
    public CommandLine(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        System.out.println("hello");

          Book book = new ScienceJournal("Book", "James","55abc555", 3,35.37, 8);
          Book book1 = new Book ("Antique", "Ralph", "22222", 1,100.0);



    }
}
