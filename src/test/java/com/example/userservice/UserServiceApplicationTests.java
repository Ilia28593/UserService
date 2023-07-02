package com.example.userservice;

import com.example.userservice.controller.Controller;
import com.example.userservice.entity.Person;
import com.example.userservice.entity.Role;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserServiceApplicationTests {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Controller controller;

    @Test
    public void test() {
        assertThat(controller).isNotNull();
    }


    @Test
    public void getTestGetAll() throws Exception {
        this.mockMvc.perform(get("/api/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andDo(print());
    }

    @Test
    void getTestGetId() throws Exception {
        mockMvc.perform(get("/api/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andDo(print());
    }

    @Test
    void getTestCreate() throws Exception {
        Person person = new Person();
        person.setLastName("Admin");
        person.setFirstName("Admin");
        person.setRole(Role.USER);
        person.setBirthday(LocalDate.of(2010, 2, 2));
        person.setPassword("123A");
        person.setEmail("admin@mail.com");
        mockMvc.perform(post("/api")
                                .content(asJsonString(person))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andDo(print());
    }

    @Test
    void getTestUpdate() throws Exception {
        Person person = new Person();
        person.setId(1L);
        person.setLastName("Admin");
        person.setFirstName("UpdateAdmin");
        person.setRole(Role.ADMIN);
        person.setBirthday(LocalDate.of(2010, 2, 2));
        person.setPassword("fdf");
        person.setEmail("xxx@mail.com");
        mockMvc.perform(put("/api")
                                .content(asJsonString(person))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andDo(print());
    }

    @Test
    public void getTestUpdateRole() throws Exception {
        mockMvc.perform(put("/api/1/change/ADMIN"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andDo(print());
    }

    private static String asJsonString(final Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule((new JavaTimeModule()));
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
