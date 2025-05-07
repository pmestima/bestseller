package com.bestseller.assignment.service.discount;

import com.bestseller.assignment.entity.CartEntity;
import com.bestseller.assignment.service.DiscountService;

public class DiscountRule3 implements DiscountService {
    @Override
    public double calculateDiscount(CartEntity cart) {
        double value1 = new DiscountRule1().calculateDiscount(cart);
        double value2 = new DiscountRule2().calculateDiscount(cart);

        return Math.min(value1, value2);
    }
}
