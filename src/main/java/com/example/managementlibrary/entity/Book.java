package com.example.managementlibrary.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Book extends Base{

    private String name;
    private String image;
    private Long count;
    private Long publishAt;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    @OneToMany(mappedBy = "book",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<CartItem> items=new ArrayList<>();

    @OneToMany(mappedBy = "book",orphanRemoval = true,cascade = CascadeType.ALL)
    List<Comment> comments=new ArrayList<>();


}
