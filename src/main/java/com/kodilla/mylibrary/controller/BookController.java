package com.kodilla.mylibrary.controller;

import com.kodilla.mylibrary.domain.Book;
import com.kodilla.mylibrary.dto.BookDto;
import com.kodilla.mylibrary.repository.BookRepository;
import com.kodilla.mylibrary.service.BookService;
import com.kodilla.mylibrary.service.WolneLekturyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private WolneLekturyService wolneLekturyService;

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto>
    getBookById(@PathVariable Long id) {
        BookDto bookDto = bookService.getBookById(id);

        return bookDto != null ? ResponseEntity.ok(bookDto) : ResponseEntity.notFound().build();
    }

    @GetMapping("/get")
    public ResponseEntity<List<BookDto>> getAllBookFromWolneLektury() {
        List<Book> books = wolneLekturyService.allBooksFromWolneLektury();
        books.stream()
                .filter(b -> b.getGenre().toLowerCase().contains("bajka"))
                .map(
                        b -> b.getGenre() + ", " + b.getTitle() + ", " + b.getAuthor() + ", " + b.getUrl()
                )
                .sorted()
                .forEach(System.out::println);
        return ResponseEntity.ok(new ArrayList<>());
    }

    @PostMapping
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto) {
        BookDto createdBook
 = bookService.saveBook(bookDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();

    }

}
