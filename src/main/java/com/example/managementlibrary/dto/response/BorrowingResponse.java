package com.example.managementlibrary.dto.response;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class BorrowingResponse {
    private Long id;
    private List<BorrowingItemResponse> borrowingItems;
}
