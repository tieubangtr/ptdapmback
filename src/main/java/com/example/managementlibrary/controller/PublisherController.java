package com.example.managementlibrary.controller;

import com.example.managementlibrary.dto.request.PublisherRequest;
import com.example.managementlibrary.dto.response.PublisherResponse;
import com.example.managementlibrary.entity.Publisher;
import com.example.managementlibrary.service.GenericService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/publishers")
public class PublisherController extends GenericAPI<Publisher,Long, PublisherRequest, PublisherResponse> {
    public PublisherController(GenericService<Publisher, Long, PublisherRequest, PublisherResponse> genericService) {
        super(genericService);
    }
}
