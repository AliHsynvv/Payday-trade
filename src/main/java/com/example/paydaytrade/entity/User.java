package com.example.paydaytrade.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.io.Serializable;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "cash")
    private Double cash;

}
