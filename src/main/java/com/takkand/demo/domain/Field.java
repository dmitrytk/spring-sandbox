package com.takkand.demo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String name;
    private String type;
    private String location;

    public Field(String name) {
        this.name = name;
    }

    public Field update(Field otherField) {
        if (otherField.getName() != null) setName(otherField.getName());
        if (otherField.getType() != null) setType(otherField.getType());
        if (otherField.getLocation() != null) setLocation(otherField.getLocation());
        return this;
    }
}
