package com.bestseller.assignment.repository;

import com.bestseller.assignment.entity.DrinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DrinkRepository extends JpaRepository<DrinkEntity, UUID> {
    Optional<DrinkEntity> findByIdAndDeletedIsFalse(UUID drinkId);
}
