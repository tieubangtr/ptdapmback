package com.example.managementlibrary.dto.response;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
@ToString
public class BorrowingItemResponse {

    private String note;

    private Date payday;

    private Boolean status;

    private BookResponse book;
}
