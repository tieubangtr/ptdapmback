package com.example.managementlibrary.controller;

import com.example.managementlibrary.dto.request.BorrowingItemRequest;
import com.example.managementlibrary.dto.response.BorrowingItemResponse;
import com.example.managementlibrary.entity.BorrowingItem;
import com.example.managementlibrary.service.GenericService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/borrowingItems")
public class BorrowingItemController extends GenericAPI<BorrowingItem,Long, BorrowingItemRequest, BorrowingItemResponse> {
    public BorrowingItemController(GenericService<BorrowingItem, Long, BorrowingItemRequest, BorrowingItemResponse> genericService) {
        super(genericService);
    }
}
