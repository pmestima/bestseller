package com.bestseller.assignment.controller;

import com.bestseller.assignment.dto.CartInfoDto;
import com.bestseller.assignment.dto.ProductDto;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final Gson gson = new Gson();

    @Test
    void addToCartTest() throws Exception {
        String productDto = """
            {
                "drink": { "id": "ad6661bd-afdc-43c5-87d3-8e157d368f67"},
                "toppings": [
                    { "id": "5b703b4b-768f-49da-8146-173bac033d5a"},
                    { "id": "74168ce1-786a-4d1d-b6f3-78bf6ddff342"}
                ]
            }
            """;

        MvcResult mvcResult = mockMvc.perform(post("/cart/1/products").content(productDto).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

        CartInfoDto result = gson.fromJson(mvcResult.getResponse().getContentAsString(), CartInfoDto.class);
        assertEquals(1, result.cartId());
        assertEquals(11, result.totalAmount());
        assertEquals(1, result.products().size());
        ProductDto expectedProduct = result.products().get(0);
        assertEquals(UUID.fromString("ad6661bd-afdc-43c5-87d3-8e157d368f67"), expectedProduct.drink().id());
        assertEquals(4, expectedProduct.drink().price());
        assertEquals(2, expectedProduct.toppings().size());
        assertEquals(UUID.fromString("5b703b4b-768f-49da-8146-173bac033d5a"), expectedProduct.toppings().get(0).id());
        assertEquals(5, expectedProduct.toppings().get(0).price());
        assertEquals(UUID.fromString("74168ce1-786a-4d1d-b6f3-78bf6ddff342"), expectedProduct.toppings().get(1).id());
        assertEquals(2, expectedProduct.toppings().get(1).price());
    }
}
