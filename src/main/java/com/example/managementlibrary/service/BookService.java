package com.example.managementlibrary.service;

import com.example.managementlibrary.dto.request.BookRequest;
import com.example.managementlibrary.dto.response.BookResponse;
import com.example.managementlibrary.entity.Book;

public interface BookService extends GenericService<Book,Long, BookRequest, BookResponse>{
}
