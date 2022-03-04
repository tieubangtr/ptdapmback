package com.example.managementlibrary.mapper;

import com.example.managementlibrary.dto.request.BookRequest;
import com.example.managementlibrary.dto.request.PublisherRequest;
import com.example.managementlibrary.dto.response.BookResponse;
import com.example.managementlibrary.dto.response.PublisherResponse;
import com.example.managementlibrary.entity.Book;
import com.example.managementlibrary.entity.Publisher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PublisherMapper {
   Publisher dtoToEntity(PublisherRequest request);
   PublisherResponse entityToDto(Publisher publisher);
}
