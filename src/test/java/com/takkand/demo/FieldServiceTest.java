package com.takkand.demo;

import com.takkand.demo.domain.Field;
import com.takkand.demo.repo.FieldRepository;
import com.takkand.demo.service.FieldService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class FieldServiceTest {

    @Autowired
    private FieldService fieldService;

    @Mock
    private FieldRepository fieldRepository;

    @Test
    @DisplayName("Test findById Success")
    void testFindById() {
        // Setup mock repository
        Field field = new Field();
        field.setName("Salym");
        Mockito.when(fieldRepository.findAll()).thenReturn(List.of(field));

        List<Field> fields = fieldService.findAll();
        Assertions.assertEquals(fields.size(), 2, "Field was not found");


    }
}
