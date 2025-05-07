package com.bestseller.assignment.dto;

import jakarta.annotation.Nonnull;

import java.util.List;

public record ProductDto(@Nonnull DrinkDto drink, List<ToppingDto> toppings) {
}
