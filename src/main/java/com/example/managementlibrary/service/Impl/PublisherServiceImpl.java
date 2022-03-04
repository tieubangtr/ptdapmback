package com.example.managementlibrary.service.Impl;

import com.example.managementlibrary.dto.request.PublisherRequest;
import com.example.managementlibrary.dto.response.PublisherResponse;
import com.example.managementlibrary.entity.Publisher;
import com.example.managementlibrary.mapper.PublisherMapper;
import com.example.managementlibrary.repository.GenericRepository;
import com.example.managementlibrary.service.GenericService;
import com.example.managementlibrary.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublisherServiceImpl extends GenericServiceImp<Publisher,Long, PublisherRequest, PublisherResponse> implements PublisherService {
    public PublisherServiceImpl(GenericRepository<Publisher, Long> repository) {
        super(repository);
    }


    @Autowired
    PublisherMapper publisherMapper;

    @Override
    public Publisher transformDTOToEntity(PublisherRequest element) {
        return publisherMapper.dtoToEntity(element);
    }

    @Override
    public PublisherResponse transformEntityToDTO(Publisher element) {
        return publisherMapper.entityToDto(element);
    }
}
