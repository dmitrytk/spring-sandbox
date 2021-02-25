package com.takkand.demo;

import com.takkand.demo.domain.Field;
import com.takkand.demo.service.FieldService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FieldControllerTest {

    @Autowired
    private FieldService fieldService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        Field field = new Field("Salym");
        fieldService.save(field);

        this.mockMvc.perform(get("/api/fields")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Salym")));
    }
}
