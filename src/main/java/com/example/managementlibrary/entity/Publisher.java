package com.example.managementlibrary.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Publisher extends Base{

    private String name;
    private String addr;
    @Column(unique = true)
    private String email;
    private String representative;
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "publisher",
            orphanRemoval = true)
    private List<Book> books = new ArrayList<>();

}
