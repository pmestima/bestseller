package com.bestseller.assignment.service.discount;

import com.bestseller.assignment.entity.CartEntity;
import com.bestseller.assignment.entity.ProductEntity;
import com.bestseller.assignment.service.DiscountService;

import java.util.Comparator;

public class DiscountRule2 implements DiscountService {
    @Override
    public double calculateDiscount(CartEntity cart) {
        if (cart.getProducts().isEmpty()) {
            return 0;
        }

        ProductEntity lowestPrice = cart.getProducts()
            .stream()
            .min(Comparator.comparing(ProductEntity::getAmount))
            .orElse(null);

        // It should never be null because I guarantee in the beginning we have at least 3 products
        return lowestPrice.getAmount();
    }
}
