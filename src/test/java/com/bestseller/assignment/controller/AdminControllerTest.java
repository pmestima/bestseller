package com.bestseller.assignment.controller;

import com.bestseller.assignment.dto.ToppingDto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final Gson gson = new Gson();

    @Test
    void getMostUsedToppingsTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/admin/most-used-toppings"))
            .andExpect(status().isOk())
            .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();

        Type type = new TypeToken<ArrayList<ToppingDto>>() {
        }.getType();
        List<ToppingDto> list = gson.fromJson(contentAsString, type);

        assertEquals(4, list.size());
    }
}
