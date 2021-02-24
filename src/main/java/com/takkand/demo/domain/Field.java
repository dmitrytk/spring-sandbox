package com.takkand.demo.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String name;
}
