package com.bestseller.assignment.repository;

import com.bestseller.assignment.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity, Integer> {
    Optional<CartEntity> findByCartIdAndCompleteIsFalse(int cartId);
}

