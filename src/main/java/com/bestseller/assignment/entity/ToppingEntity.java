package com.bestseller.assignment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "topping")
@Getter
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class ToppingEntity {
    @Id private final UUID id;
    @Setter private String name;
    @Setter private double price;
    @Setter private int nOrders;
    @Setter private boolean deleted = false;

    public ToppingEntity(UUID id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.deleted = false;
    }

    public void increaseOrder() {
        nOrders++;
    }
}
