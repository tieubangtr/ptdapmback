package com.example.managementlibrary.service.Impl;

import com.example.managementlibrary.dto.request.BorrowingRequest;
import com.example.managementlibrary.dto.response.BorrowingResponse;
import com.example.managementlibrary.entity.Borrowing;
import com.example.managementlibrary.mapper.BorrowingMapper;
import com.example.managementlibrary.repository.GenericRepository;
import com.example.managementlibrary.service.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BorrowingServiceImpl extends GenericServiceImp<Borrowing,Long, BorrowingRequest, BorrowingResponse> implements BorrowingService {
    public BorrowingServiceImpl(GenericRepository<Borrowing, Long> repository) {
        super(repository);
    }

    @Autowired
    BorrowingMapper borrowingMapper;

    @Override
    public Borrowing transformDTOToEntity(BorrowingRequest element) {
        return borrowingMapper.dtoToEntity(element);
    }

    @Override
    public BorrowingResponse transformEntityToDTO(Borrowing element) {
        return borrowingMapper.entityToDto(element);
    }
}
