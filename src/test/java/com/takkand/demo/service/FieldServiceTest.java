package com.takkand.demo.service;


import com.takkand.demo.domain.Field;
import com.takkand.demo.repo.FieldRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;


@SpringBootTest
public class FieldServiceTest {
    @Autowired
    private FieldService fieldService;

    @MockBean
    private FieldRepository fieldRepository;

    @Test
    void testFindById() {
        Field field = new Field("Salym");
        doReturn(Optional.of(field)).when(fieldRepository).findById(1L);

        Optional<Field> findField = fieldService.findById(1L);

        Assertions.assertTrue(findField.isPresent());
        Assertions.assertEquals(findField.get().getName(), "Salym");

    }

    @Test
    void testFindAll() {
        doReturn(List.of(new Field("Salym"), new Field("Carichan")))
                .when(fieldRepository).findAll();
        List<Field> allFields = fieldService.findAll();

        Assertions.assertEquals(allFields.size(), 2);

    }

    @Test
    void testSave() {
        Field field = new Field("Salym");
        doReturn(field).when(fieldRepository).save(any());

        Field savedField = fieldService.save(field);

        Assertions.assertNotNull(savedField);
        Assertions.assertEquals(savedField.getName(), "Salym");

    }


}
