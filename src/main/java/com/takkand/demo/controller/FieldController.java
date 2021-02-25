package com.takkand.demo.controller;

import com.takkand.demo.domain.Field;
import com.takkand.demo.exception.ResourceNotFoundException;
import com.takkand.demo.service.FieldService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fields")
public class FieldController {

    private static final String API_URL = "/api/fields";

    private final FieldService fieldService;

    public FieldController(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @GetMapping("/{id}")
    ResponseEntity<Field> one(@PathVariable Long id) {
        return fieldService.findById(id)
                .map(field -> new ResponseEntity<>(field, HttpStatus.OK))
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @GetMapping
    ResponseEntity<List<Field>> all() {
        return new ResponseEntity<>(fieldService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Field> create(@RequestBody Field newField) {
        return new ResponseEntity<>(fieldService.save(newField), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<Field> update(@RequestBody Field newField, @PathVariable Long id) {
        Optional<Field> existingField = fieldService.findById(id);
        if (existingField.isEmpty()) {
            throw new ResourceNotFoundException(id);
        }
        Field field = existingField.get().update(newField);
        return new ResponseEntity<>(fieldService.save(field), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        fieldService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
