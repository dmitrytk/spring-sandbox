package com.takkand.demo;

import com.takkand.demo.domain.Field;
import com.takkand.demo.repo.FieldRepository;
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
    void testFindById() {
        Field field = new Field();
        field.setName("Salym");
        fieldRepository.save(field);
        Optional<Field> returnField = fieldRepository.findById(1L);
        List<Field> fieldList = fieldRepository.findAll();

        Assertions.assertTrue(returnField.isPresent());
        Assertions.assertEquals(returnField.get().getName(), "Salym");
        Assertions.assertEquals(fieldList.size(), 1);
    }
}
