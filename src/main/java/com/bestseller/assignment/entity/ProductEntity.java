package com.bestseller.assignment.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Table(name = "product")
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class ProductEntity {
    private @Id final UUID productId;
    private DrinkEntity drink;
    private @ManyToMany List<ToppingEntity> toppings;
    private double amount;
}
