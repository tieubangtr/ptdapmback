package com.example.managementlibrary.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Borrowing extends Base {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @CreatedDate
    @Column(updatable = false)
    private Date borrowedDate;

    @OneToMany(mappedBy = "borrowing",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<BorrowingItem> borrowingItems=new ArrayList<>();
}
