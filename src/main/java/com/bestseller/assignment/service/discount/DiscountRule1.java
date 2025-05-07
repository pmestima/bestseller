package com.bestseller.assignment.service.discount;

import com.bestseller.assignment.entity.CartEntity;
import com.bestseller.assignment.service.DiscountService;

public class DiscountRule1 implements DiscountService {
    @Override
    public double calculateDiscount(CartEntity cart) {
        return cart.getTotalAmount() * .25;
    }
}
