package com.example.managementlibrary.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@Setter
public class RefreshToken {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @OneToOne
  @JoinColumn(name = "user_id")
  @MapsId
  private User user;
  @Column(nullable = false, unique = true)
  private String token;
  @Column(nullable = false)
  private Instant expiryDate;

}