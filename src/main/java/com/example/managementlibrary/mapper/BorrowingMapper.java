package com.example.managementlibrary.mapper;

import com.example.managementlibrary.dto.request.BorrowingRequest;
import com.example.managementlibrary.dto.response.BorrowingResponse;
import com.example.managementlibrary.entity.Borrowing;
import lombok.Data;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface BorrowingMapper {

    @Mapping(source = "request.userId",target = "user.id")
    Borrowing dtoToEntity(BorrowingRequest request);
    BorrowingResponse entityToDto(Borrowing borrowing);
}
