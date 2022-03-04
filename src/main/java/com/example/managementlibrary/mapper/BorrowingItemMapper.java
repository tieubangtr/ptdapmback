package com.example.managementlibrary.mapper;

import com.example.managementlibrary.dto.request.BorrowingItemRequest;
import com.example.managementlibrary.dto.request.BorrowingRequest;
import com.example.managementlibrary.dto.response.BorrowingItemResponse;
import com.example.managementlibrary.dto.response.BorrowingResponse;
import com.example.managementlibrary.entity.Borrowing;
import com.example.managementlibrary.entity.BorrowingItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BorrowingItemMapper {
    @Mapping(source = "request.bookId",target = "book.id")
    @Mapping(source = "request.borrowingId",target = "borrowing.id")
    BorrowingItem dtoToEntity(BorrowingItemRequest request);

    BorrowingItemResponse entityToDto(BorrowingItem borrowing);
}
