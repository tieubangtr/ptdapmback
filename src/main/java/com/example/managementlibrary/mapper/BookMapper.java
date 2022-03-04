package com.example.managementlibrary.mapper;

import com.example.managementlibrary.dto.request.BookRequest;
import com.example.managementlibrary.dto.response.BookResponse;
import com.example.managementlibrary.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(source = "request.authorId",target = "author.id")
    @Mapping(source = "request.publisherId",target = "publisher.id")
    @Mapping(source = "request.categoryId",target = "category.id")
    Book dtoToEntity(BookRequest request);
    BookResponse entityToDto(Book book);
}
