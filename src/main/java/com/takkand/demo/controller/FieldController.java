package com.takkand.demo.controller;

import com.takkand.demo.domain.Field;
import com.takkand.demo.exception.ResourceNotFoundException;
import com.takkand.demo.service.FieldService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fields")
public class FieldController {

    private final FieldService fieldService;

    public FieldController(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @GetMapping("/{id}")
    Field one(@PathVariable Long id) {
        return fieldService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    // BASIC
    @GetMapping
    List<Field> all() {
        return fieldService.findAll();
    }

    @PostMapping
    Field create(@RequestBody Field newField) {
        return fieldService.save(newField);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        fieldService.deleteById(id);
    }
}
