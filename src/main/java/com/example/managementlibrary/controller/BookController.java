package com.example.managementlibrary.controller;

import com.example.managementlibrary.dto.request.BookRequest;
import com.example.managementlibrary.dto.response.BookResponse;
import com.example.managementlibrary.entity.Book;
import com.example.managementlibrary.service.GenericService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/books")
public class BookController extends GenericAPI<Book,Long, BookRequest, BookResponse> {
    public BookController(GenericService<Book, Long, BookRequest, BookResponse> genericService) {
        super(genericService);
    }
}
