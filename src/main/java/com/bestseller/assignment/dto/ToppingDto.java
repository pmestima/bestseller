package com.bestseller.assignment.dto;

import jakarta.annotation.Nonnull;

import java.util.UUID;

public record ToppingDto(@Nonnull UUID id, String name, double price) {
}
