package com.takkand.demo.controller;

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

import static com.takkand.demo.util.StringUtils.asJsonString;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class FieldControllerTest {

    private static final String API_URL = "/api/fields";
    private static final String API_URL_WITH_ID = "/api/fields/{id}";

    @MockBean
    private FieldService fieldService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testOne() throws Exception {
        doReturn(Optional.of(new Field("Carichan"))).when(fieldService).findById(1L);
        mockMvc.perform(get(API_URL_WITH_ID, 1L)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Carichan")));
    }

    @Test
    public void testAll() throws Exception {
        doReturn(List.of(new Field("Salym"), new Field("Carichan"))).when(fieldService).findAll();

        mockMvc.perform(get(API_URL)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Carichan")))
                .andExpect(content().string(containsString("Salym")));
    }

    @Test
    public void testCreate() throws Exception {
        Field fieldToSave = new Field("Salym");
        Field fieldToReturn = new Field("Salym");
        fieldToReturn.setId(1);
        doReturn(fieldToReturn).when(fieldService).save(any());

        mockMvc.perform(post(API_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(fieldToSave)))

                // Validate the response
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Salym")));
    }

    @Test
    public void testUpdate() throws Exception {
        // Setup our mocked service
        Field fieldToPut = new Field("NewSalym");
        Field fieldToFind = new Field(1L, "OldSalym");
        Field fieldToReturn = new Field(1L, "NewSalym");
        doReturn(Optional.of(fieldToFind)).when(fieldService).findById(1L);
        doReturn(fieldToReturn).when(fieldService).save(any());

        // Execute the POST request
        mockMvc.perform(put(API_URL_WITH_ID, 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(fieldToPut)))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("NewSalym")));
    }

    @Test
    public void testDelete() throws Exception {
        doNothing().when(fieldService).deleteById(1L);

        // Execute the POST request
        mockMvc.perform(delete(API_URL_WITH_ID, 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString("field")))

                .andExpect(status().isNoContent());
    }
}
