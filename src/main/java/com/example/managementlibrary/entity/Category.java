package com.example.managementlibrary.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Category extends Base{

    private String name;
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "category",
            orphanRemoval = true)
    private List<Book> books = new ArrayList<>();
}
