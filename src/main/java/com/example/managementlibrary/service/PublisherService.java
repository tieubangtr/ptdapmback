package com.example.managementlibrary.service;

import com.example.managementlibrary.dto.request.PublisherRequest;
import com.example.managementlibrary.dto.response.PublisherResponse;
import com.example.managementlibrary.entity.Publisher;

public interface PublisherService extends GenericService<Publisher,Long, PublisherRequest, PublisherResponse> {
}
