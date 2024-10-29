package com.kodilla.mylibrary.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodilla.mylibrary.domain.Book;
import com.kodilla.mylibrary.dto.BookDto;
import com.kodilla.mylibrary.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class WolneLekturyService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BookMapper bookMapper;



    public List<Book> allBookFromWolneLektury() {
        String url = "https://wolnelektury.pl/api/books";

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            JsonNode root = new ObjectMapper().readTree(response.getBody());

            List<Book> books = new ArrayList<>();
            for (JsonNode resultNode : root) {
                BookDto bookDto = new BookDto();
                bookDto.setTitle(resultNode.get("title").asText());
                bookDto.setUrl(resultNode.get("url").asText());
                books.add(bookMapper.toEntity(bookDto));
            }

            return books;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Book findBookByTitle(String title) {

        String url = "https://wolnelektury.pl/api/books";

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            JsonNode root = new ObjectMapper().readTree(response.getBody());

            JsonNode bookNode = root.get("results").get(0);

            BookDto bookDto = new BookDto();
            bookDto.setTitle(bookNode.get("title").asText());
            bookDto.setUrl(bookNode.get("url").asText());

            return bookMapper.toEntity(bookDto);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
