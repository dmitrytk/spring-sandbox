package com.takkand.demo.controller;

import com.takkand.demo.domain.Field;
import com.takkand.demo.exception.ResourceNotFoundException;
import com.takkand.demo.service.FieldService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
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
    ResponseEntity<?> one(@PathVariable Long id) {
        return fieldService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @GetMapping
    ResponseEntity<List<Field>> all() {
        return ResponseEntity.ok(fieldService.findAll());
    }

    @PostMapping
    ResponseEntity<Field> create(@RequestBody Field newField) {
        Field field = fieldService.save(newField);
        try {
            return ResponseEntity
                    .created(new URI(API_URL + field.getId()))
                    .body(fieldService.save(newField));
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Field> update(@RequestBody Field newField, @PathVariable Long id) {
        Optional<Field> existingField = fieldService.findById(id);
        if (existingField.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        Field field = existingField.get().update(newField);
        Field updatedField = fieldService.save(field);

        try {
            return ResponseEntity
                    .ok()
                    .location(new URI(API_URL + updatedField.getId()))
                    .body(updatedField);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity delete(@PathVariable Long id) {
        return fieldService.findById(id)
                .map(field -> {
                    fieldService.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElseThrow(ResourceNotFoundException::new);

    }
}
