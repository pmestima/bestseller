package com.bestseller.assignment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Table(name = "cart")
@Entity
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class CartEntity {

    @Getter
    @Id
    private final int cartId;

    @OneToMany
    private List<ProductEntity> products = new ArrayList<>();

    @Getter
    private double totalAmount;

    @Getter
    @Setter
    private double discountAmount;

    @Getter
    @Setter
    private boolean complete;

    public void addProduct(ProductEntity newProduct) {
        totalAmount += newProduct.getAmount();
        products.add(newProduct);
    }

    /**
     * Should be an immutable list so no one can add or remove products
     * This way, they have to use addProduct() which will calculate the total
     */
    public List<ProductEntity> getProducts() {
        return Collections.unmodifiableList(products);
    }
}
