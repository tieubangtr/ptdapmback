package com.example.managementlibrary.dto.request;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class BorrowingRequest {
    Long UserId;
    private Date borrowedDate;
}
