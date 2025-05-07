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

class DiscountRule1Test {

    @InjectMocks
    private DiscountRule1 discountRule1 = new DiscountRule1();

    @Test
    void testTotalAmount() {
        CartEntity cart = new CartEntity(1);

        DrinkEntity drink = new DrinkEntity(UUID.randomUUID(), "drink", 1);
        ToppingEntity topping = new ToppingEntity(UUID.randomUUID(), "topping", 2);

        cart.addProduct(new ProductEntity(UUID.randomUUID(), drink, List.of(topping), 3));
        double discount = discountRule1.calculateDiscount(cart);
        assertEquals(.75, discount);
    }
}
