package com.bestseller.assignment.service;

import com.bestseller.assignment.entity.CartEntity;
import com.bestseller.assignment.service.discount.DiscountRule1;
import com.bestseller.assignment.service.discount.DiscountRule2;
import com.bestseller.assignment.service.discount.DiscountRule3;
import com.bestseller.assignment.service.discount.NoDiscount;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DiscountFactory {

    /**
     * For now, it's only 2 properties, if this grows, can be extracted to a config file
     */
    @Value("${bestseller.discount.total-amount:12}")
    private double minimumAmountForDiscount;

    @Value("${bestseller.discount.products-in-cart:3}")
    private double nProductsInCart;

    public DiscountService getDiscountService(CartEntity cart) {
        boolean moreThan12euros = cart.getTotalAmount() > minimumAmountForDiscount;
        boolean moreThan3Products = cart.getProducts().size() >= nProductsInCart;

        if (moreThan12euros && moreThan3Products) {
            return new DiscountRule3();
        }
        if (moreThan12euros) {
            return new DiscountRule1();
        }
        if (moreThan3Products) {
            return new DiscountRule2();
        }
        return new NoDiscount();
    }
}
