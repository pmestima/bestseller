package com.bestseller.assignment.repository;

import com.bestseller.assignment.entity.ToppingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ToppingRepository extends JpaRepository<ToppingEntity, UUID> {

    Optional<ToppingEntity> findByIdAndDeletedIsFalse(UUID id);

    @Query("SELECT t FROM ToppingEntity t ORDER BY t.nOrders DESC")
    List<ToppingEntity> mostUsedToppings();
}
