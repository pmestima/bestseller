package com.bestseller.assignment.mapper;

import com.bestseller.assignment.dto.ProductDto;
import com.bestseller.assignment.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final DrinkMapper drinkMapper;
    private final ToppingMapper toppingMapper;

    public ProductDto toDto(ProductEntity productEntity) {
        return new ProductDto(
            drinkMapper.toDto(productEntity.getDrink()),
            productEntity.getToppings().stream()
                .map(toppingMapper::toDto)
                .toList()
        );
    }
}
