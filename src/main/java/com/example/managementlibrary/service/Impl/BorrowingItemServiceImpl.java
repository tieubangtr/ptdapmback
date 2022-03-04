package com.example.managementlibrary.service.Impl;

import com.example.managementlibrary.dto.request.BorrowingItemRequest;
import com.example.managementlibrary.dto.request.BorrowingRequest;
import com.example.managementlibrary.dto.response.BorrowingItemResponse;
import com.example.managementlibrary.entity.BorrowingItem;
import com.example.managementlibrary.mapper.BorrowingItemMapper;
import com.example.managementlibrary.mapper.BorrowingMapper;
import com.example.managementlibrary.repository.GenericRepository;
import com.example.managementlibrary.service.BorrowingItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BorrowingItemServiceImpl extends GenericServiceImp<BorrowingItem,Long, BorrowingItemRequest, BorrowingItemResponse> implements BorrowingItemService {
    public BorrowingItemServiceImpl(GenericRepository<BorrowingItem, Long> repository) {
        super(repository);
    }

    @Autowired
    BorrowingItemMapper borrowingItemMapper;

    @Override
    public BorrowingItem transformDTOToEntity(BorrowingItemRequest element) {
        return borrowingItemMapper.dtoToEntity(element);
    }

    @Override
    public BorrowingItemResponse transformEntityToDTO(BorrowingItem element) {
        return borrowingItemMapper.entityToDto(element);
    }
}
