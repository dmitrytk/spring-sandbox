package com.takkand.demo;

import com.takkand.demo.domain.Field;
import com.takkand.demo.repo.FieldRepository;
import com.takkand.demo.service.FieldService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.doReturn;

@SpringBootTest
public class FieldServiceTest {

    @Autowired
    private FieldService fieldService;

    @MockBean
    private FieldRepository fieldRepository;

    @Test
    @DisplayName("Test findById Success")
    void testFindById() {
        // Setup our mock repository
        Field field = new Field();
        field.setName("Salym");
        doReturn(Optional.of(field)).when(fieldRepository).findById(1L);

        Optional<Field> returnField = fieldService.findById(1L);
        Assertions.assertTrue(returnField.isPresent(), "Field was not found");


    }
}
