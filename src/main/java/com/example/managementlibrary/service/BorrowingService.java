package com.example.managementlibrary.service;

import com.example.managementlibrary.dto.request.BorrowingRequest;
import com.example.managementlibrary.dto.response.BorrowingResponse;
import com.example.managementlibrary.entity.Borrowing;

public interface BorrowingService extends GenericService<Borrowing,Long, BorrowingRequest, BorrowingResponse>{
}
