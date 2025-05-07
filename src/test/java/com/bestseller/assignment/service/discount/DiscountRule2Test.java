package com.bestseller.assignment.service.discount;

import com.bestseller.assignment.entity.CartEntity;
import com.bestseller.assignment.entity.DrinkEntity;
import com.bestseller.assignment.entity.ProductEntity;
import com.bestseller.assignment.entity.ToppingEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class DiscountRule2Test {

    @InjectMocks
    private DiscountRule2 discountRule2 = new DiscountRule2();

    @Test
    void shouldReturnZeroTest() {
        CartEntity cart = new CartEntity(1);
        double discount = discountRule2.calculateDiscount(cart);
        assertEquals(0.0, discount);
    }

    @Test
    void shouldReturn3ProductsTest() {
        CartEntity cart = new CartEntity(1);

        DrinkEntity drink = new DrinkEntity(UUID.randomUUID(), "drink", 1);
        ToppingEntity topping = new ToppingEntity(UUID.randomUUID(), "topping", 2);

        cart.addProduct(new ProductEntity(UUID.randomUUID(), drink, List.of(topping), 5));
        cart.addProduct(new ProductEntity(UUID.randomUUID(), drink, List.of(topping), 2));
        cart.addProduct(new ProductEntity(UUID.randomUUID(), drink, List.of(topping), 4));
        double discount = discountRule2.calculateDiscount(cart);
        assertEquals(2.0, discount);
    }
}
