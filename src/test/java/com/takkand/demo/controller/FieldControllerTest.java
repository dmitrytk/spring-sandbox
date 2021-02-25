package com.takkand.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.takkand.demo.domain.Field;
import com.takkand.demo.service.FieldService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class FieldControllerTest {

    @MockBean
    private FieldService fieldService;

    @Autowired
    private MockMvc mockMvc;

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGetFieldById() throws Exception {
        doReturn(Optional.of(new Field("Carichan"))).when(fieldService).findById(1L);
        mockMvc.perform(get("/api/fields/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Carichan")));
    }

    @Test
    public void testGetAllFields() throws Exception {
        doReturn(List.of(new Field("Salym"), new Field("Carichan"))).when(fieldService).findAll();

        mockMvc.perform(get("/api/fields")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Carichan")))
                .andExpect(content().string(containsString("Salym")));
    }

    @Test
    public void testCreateFieldBy() throws Exception {
        Field fieldToSave = new Field("Salym");
        Field fieldToReturn = new Field("Salym");
        fieldToReturn.setId(1);
        doReturn(fieldToReturn).when(fieldService).save(any());

        mockMvc.perform(post("/api/fields")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(fieldToSave)))

                // Validate the response
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Salym")));
    }
}
