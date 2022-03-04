package com.example.managementlibrary.service;

import com.example.managementlibrary.common.Filter;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface GenericService<E,I,D1,D2> {
    /**
     * E : Entity Class
     * I : type of Id element
     * D1 : DTO request POJO
     * D2 : DTO response POJO
     */

    public D2 get(I id);
    public Page<D2> getPage(int page , int limit, String sort, List<Filter> filters);
    public List<D2> getAll();
    public D2 update(D1 element,I id);
    public D2 create(D1 element);
    public void delete(I id);
    public boolean isExist(I id);
    public E transformDTOToEntity(D1 element);
    public D2 transformEntityToDTO(E element);
    public Specification<E> getSpecification(List<Filter> filters);
}