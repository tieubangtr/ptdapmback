package com.example.managementlibrary.service.Impl;


import com.example.managementlibrary.common.Filter;
import com.example.managementlibrary.entity.CartItem;
import com.example.managementlibrary.exception.GenericException;
import com.example.managementlibrary.repository.GenericRepository;
import com.example.managementlibrary.service.GenericService;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.Specification.where;


public abstract class GenericServiceImp<E, I extends Serializable, D1,D2> implements GenericService<E, I, D1,D2> {


    private GenericRepository<E, I> repository;

    public GenericServiceImp(GenericRepository<E, I> repository) {
        this.repository = repository;
    }


    @Override
    public D2 get(I id) {
        return transformEntityToDTO(repository.findById(id)
                .orElseThrow(() -> new GenericException("Id = " + id + " does not exist")));

    }

    @Override
    public List<D2> getAll() {

        List<E> list = repository.findAll();
        return list.stream().map(e -> transformEntityToDTO(e)).collect(Collectors.toList());
    }

    private void setOrders(String sort, List<Sort.Order> orders) {
        Sort.Order order;
        if (sort.contains("-")) {
            // sort=-price
            sort = sort.substring(1);
            // sort=price
            order = new Sort.Order(Sort.Direction.DESC, sort);
            orders.add(order);
        } else if (sort.contains(" ")) {
            sort = sort.substring(1);
            order = new Sort.Order(Sort.Direction.ASC, sort);
            orders.add(order);
        } else {
            order = new Sort.Order(Sort.Direction.ASC, sort);
        }
        orders.add(order);
    }

    @Override
    public Page<D2> getPage(int page, int limit, String sort, List<Filter> filters) {
        List<Sort.Order> orders = new ArrayList<>();
        //sort=+id,-price,...
        String[] sorts = sort.split(",");
        Arrays.stream(sorts).forEach(i -> setOrders(i, orders));
        Sort sorting = Sort.by(orders);
        Pageable pageable = PageRequest.of(page, limit, sorting);
        Optional<List<Filter>> optionalFilters = Optional.ofNullable(filters);
        if (optionalFilters.isPresent()) {
            try {
                return repository.findAll(getSpecification(filters), pageable)
                        .map(this::transformEntityToDTO);
            } catch (InvalidDataAccessApiUsageException e) {
                throw new GenericException("Field does not exist ");
            }
        }
        return repository.findAll(pageable)
                .map(this::transformEntityToDTO);
    }

    @Override
    public Specification<E> getSpecification(List<Filter> filters) {

        Specification<E> specification =
                where(createSpecification(filters.remove(0)));
        for (Filter input : filters) {
            specification = specification.and(createSpecification(input));
        }
        return specification;

    }

    private Object castToRequiredType(Class fieldType, String value) {
        if (fieldType.isAssignableFrom(Double.class)) {
            return Double.valueOf(value);
        } else if (fieldType.isAssignableFrom(Integer.class)) {
            return Integer.valueOf(value);
        } else if (Enum.class.isAssignableFrom(fieldType)) {
            return Enum.valueOf(fieldType, value);
        } else if (fieldType.isAssignableFrom(String.class)) {
            return String.valueOf(value);
        }
        else if(fieldType.isAssignableFrom(Long.class)){
            return Long.valueOf(value);
        }
        return null;
    }

    private Object castToRequiredType(Class fieldType, List<String> value) {
        List<Object> lists = new ArrayList<>();
        for (String s : value) {
            lists.add(castToRequiredType(fieldType, s));
        }
        return lists;
    }

    private Specification<E> createSpecification(Filter input) {
        switch (input.getOperator()) {
            case EQUALS:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.equal(root.get(input.getField()),
                                castToRequiredType(root.get(input.getField()).getJavaType(),
                                        input.getValue()));

            case NOT_EQUALS:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.notEqual(root.get(input.getField()),
                                castToRequiredType(root.get(input.getField()).getJavaType(),
                                        input.getValue()));

            case GREATER_THAN:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.gt(root.get(input.getField()),
                                (Number) castToRequiredType(
                                        root.get(input.getField()).getJavaType(),
                                        input.getValue()));

            case LESS_THAN:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.lt(root.get(input.getField()),
                                (Number) castToRequiredType(
                                        root.get(input.getField()).getJavaType(),
                                        input.getValue()));

            case LIKE:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.like(root.get(input.getField()),
                                "%" + input.getValue() + "%");

            case IN:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.in(root.get(input.getField()))
                                .value(castToRequiredType(
                                        root.get(input.getField()).getJavaType(),
                                        input.getValues()));
            default:
                throw new GenericException("Operation not supported yet");
        }


    }

    @Override
    @Transactional
    public D2 create(D1 element) {
        try {
            return transformEntityToDTO(repository.save(transformDTOToEntity(element)));
        } catch (Exception e) {
            throw new GenericException("Error occurred when adding");
        }

    }

    @Override
    @Transactional
    public D2 update(D1 element, I id) {
        if (isExist(id)) {
            try {
                setId(id, element, "id");
                E e = repository.save(transformDTOToEntity(element));
                return this.transformEntityToDTO(e);
            } catch (Exception e) {
                throw new GenericException("error occurred when updating");
            }
        } else {
            throw new GenericException("Id = " + id + " does not exist");
        }
    }

    private void setId(I id, Object trg, String props) {
        BeanWrapper trgWrap = PropertyAccessorFactory.forBeanPropertyAccess(trg);
        trgWrap.setPropertyValue(props, id);
    }

    @Override
    @Transactional
    public void delete(I id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new GenericException("Error occurred when deleting");
        }

    }

    @Override
    public boolean isExist(I id) {
        return repository.existsById(id);
    }

    @Override
    public E transformDTOToEntity(D1 element) {
        return null;
    }

    @Override
    public D2 transformEntityToDTO(E element) {
        return null;
    }

}
