package com.example.managementlibrary.service;

import com.example.managementlibrary.dto.request.BorrowingItemRequest;
import com.example.managementlibrary.dto.request.BorrowingRequest;
import com.example.managementlibrary.dto.response.BorrowingItemResponse;
import com.example.managementlibrary.dto.response.BorrowingResponse;
import com.example.managementlibrary.entity.Borrowing;
import com.example.managementlibrary.entity.BorrowingItem;

public interface BorrowingItemService extends GenericService<BorrowingItem,Long, BorrowingItemRequest, BorrowingItemResponse> {
}
