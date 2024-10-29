package com.kodilla.mylibrary.service;

import com.kodilla.mylibrary.domain.Book;
import com.kodilla.mylibrary.dto.BookDto;
import com.kodilla.mylibrary.mapper.BookMapper;
import com.kodilla.mylibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    public List<BookDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(book -> bookMapper.toDto(book))
                .collect(Collectors.toList());
    }

    public BookDto
    getBookById(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        return book != null ? bookMapper.toDto(book) : null;
    }

    public BookDto saveBook(BookDto bookDto) {
        Book book = bookMapper.toEntity(bookDto);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toDto(savedBook);

    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
