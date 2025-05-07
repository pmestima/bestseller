package com.bestseller.assignment.service;

import com.bestseller.assignment.dto.DrinkDto;
import com.bestseller.assignment.entity.DrinkEntity;
import com.bestseller.assignment.exception.DrinkNotFoundException;
import com.bestseller.assignment.mapper.DrinkMapper;
import com.bestseller.assignment.repository.DrinkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DrinkService {

    private final DrinkRepository drinkRepository;
    private final DrinkMapper drinkMapper;

    public Optional<DrinkEntity> getDrink(UUID drinkId) {
        return drinkRepository.findByIdAndDeletedIsFalse(drinkId);
    }

    public DrinkDto create(DrinkDto drink) {
        DrinkEntity entity = new DrinkEntity(drink.id(), drink.name(), drink.price());
        entity = drinkRepository.save(entity);
        log.info("Drink {} created", entity.getId());
        return drinkMapper.toDto(entity);
    }

    public DrinkDto update(UUID drinkId, DrinkDto drinkDto) {
        DrinkEntity entity = drinkRepository.findById(drinkId)
            .orElseThrow(DrinkNotFoundException::new);
        entity.setName(drinkDto.name());
        entity.setPrice(drinkDto.price());
        return drinkMapper.toDto(entity);
    }

    public DrinkDto delete(UUID drinkId) {
        DrinkEntity entity = drinkRepository.findById(drinkId)
            .orElseThrow(DrinkNotFoundException::new);
        entity.setDeleted(true);
        log.info("Drink {} marked as deleted", drinkId);
        return drinkMapper.toDto(entity);
    }
}
