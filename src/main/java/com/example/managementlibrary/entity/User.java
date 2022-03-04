package com.example.managementlibrary.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
public class User extends Base {

    private String name;

    @Column(unique = true)
    private String email;
    private String password;
    private Date birthday;
    private String gender;
    private String addr;
    @Column(unique = true)
    private String phone;
    private String img;
    private String token;
    private boolean isNoneLocked;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id")
            , inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    private Cart cart;

    public void addCart(){
        if(this.cart==null){
            this.cart=new Cart();
        }
        this.cart.setUser(this);
    }

    @OneToMany(mappedBy = "user",orphanRemoval = true,cascade = CascadeType.ALL)
    List<Comment> comments=new ArrayList<>();

}
