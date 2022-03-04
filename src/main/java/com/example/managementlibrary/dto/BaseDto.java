package com.example.managementlibrary.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class BaseDto {
   protected Long id;
   protected Long createdBy;
   protected LocalDateTime createdAt;
   protected Long updatedBy;
   protected LocalDateTime updatedAt;
}
