package com.takkand.demo.service;

import com.takkand.demo.domain.Field;
import com.takkand.demo.repo.FieldRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FieldServiceImpl implements FieldService {
    private final FieldRepository fieldRepository;

    public FieldServiceImpl(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
    }

    @Override
    public Optional<Field> findById(Long id) {
        return fieldRepository.findById(id);
    }

    @Override
    public List<Field> findAll() {
        return fieldRepository.findAll();
    }

    @Override
    public Field save(Field field) {
        return fieldRepository.save(field);
    }


    @Override
    public void deleteById(Long id) {
        fieldRepository.deleteById(id);
    }
}
