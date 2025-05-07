package com.bestseller.assignment.service;

import com.bestseller.assignment.entity.CartEntity;

public interface DiscountService {
    double calculateDiscount(CartEntity cart);
}
