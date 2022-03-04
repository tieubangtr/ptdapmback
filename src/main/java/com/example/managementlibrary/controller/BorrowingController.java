package com.example.managementlibrary.controller;

import com.example.managementlibrary.dto.request.BorrowingItemRequest;
import com.example.managementlibrary.dto.request.BorrowingRequest;
import com.example.managementlibrary.dto.response.BorrowingItemResponse;
import com.example.managementlibrary.dto.response.BorrowingResponse;
import com.example.managementlibrary.entity.Borrowing;
import com.example.managementlibrary.entity.BorrowingItem;
import com.example.managementlibrary.service.GenericService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/borrowings")
public class BorrowingController extends GenericAPI<Borrowing,Long, BorrowingRequest, BorrowingResponse>{
    public BorrowingController(GenericService<Borrowing, Long, BorrowingRequest, BorrowingResponse> genericService) {
        super(genericService);
    }
}
