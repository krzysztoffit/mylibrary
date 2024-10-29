package com.kodilla.mylibrary.mapper;

import com.kodilla.mylibrary.domain.Book;
import com.kodilla.mylibrary.dto.BookDto;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public BookDto toDto(Book book) {
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getDescription(),
                book.getUrl()
        );
    }

    public Book toEntity(BookDto bookDto) {
        return new Book(
                bookDto.getId(),
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getDescription(),
                bookDto.getUrl()
        );

    }

}
