package com.kodilla.mylibrary.repository;

import com.kodilla.mylibrary.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    @Override
    List<Book> findAll();

    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByAuthor(String author);
}
