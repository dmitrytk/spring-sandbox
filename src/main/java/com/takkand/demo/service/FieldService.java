package com.takkand.demo.service;

import com.takkand.demo.domain.Field;

import java.util.List;
import java.util.Optional;

public interface FieldService {
    Optional<Field> findById(Long id);
    List<Field> findAll();
    Field save(Field field);
    void deleteById(Long id);
}
