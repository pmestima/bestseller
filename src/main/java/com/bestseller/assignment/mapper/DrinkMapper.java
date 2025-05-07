package com.bestseller.assignment.mapper;

import com.bestseller.assignment.dto.DrinkDto;
import com.bestseller.assignment.entity.DrinkEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DrinkMapper {

    public DrinkDto toDto(DrinkEntity drinkEntity) {
        return new DrinkDto(drinkEntity.getId(), drinkEntity.getName(), drinkEntity.getPrice());
    }
}
