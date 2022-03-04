package com.example.managementlibrary.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Cart{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private User user;


    @OneToMany(mappedBy = "cart",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<CartItem> cartItems=new ArrayList<>();
}
