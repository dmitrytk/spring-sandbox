package com.takkand.demo.repo;

import com.takkand.demo.domain.Field;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class FieldRepositoryTest {

    @Autowired
    private FieldRepository fieldRepository;

    @Test
    void testRepo() {
        // Create field
        Field field = new Field();
        field.setName("Salym");
        fieldRepository.save(field);

        // Get by id
        Optional<Field> returnField = fieldRepository.findById(1L);
        Assertions.assertTrue(returnField.isPresent());
        Assertions.assertEquals(returnField.get().getName(), "Salym");

        // Get all
        List<Field> fieldList = fieldRepository.findAll();
        Assertions.assertEquals(fieldList.size(), 1);

        // Delete by id
        fieldRepository.deleteById(1L);
        Assertions.assertEquals(fieldRepository.findAll().size(), 0);

    }
}
