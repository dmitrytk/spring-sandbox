package com.takkand.demo.controller;

import com.takkand.demo.domain.Field;
import com.takkand.demo.repo.FieldRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fields")
public class FieldController {

    private final FieldRepository fieldRepository;

    public FieldController(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
    }

    // BASIC
    @GetMapping
    List<Field> all() {
        return fieldRepository.findAll();
    }

    @PostMapping
    Field create(@RequestBody Field newField) {
        return fieldRepository.save(newField);
    }
}
