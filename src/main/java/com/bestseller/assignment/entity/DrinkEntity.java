package com.bestseller.assignment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Table(name = "drink")
@Entity
@Getter
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class DrinkEntity implements Serializable {
    @Id private final UUID id;
    @Setter private String name;
    @Setter private double price;
    @Setter private boolean deleted = false;

    public DrinkEntity(UUID id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.deleted = false;
    }
}
