package com.bestseller.assignment.service;

import com.bestseller.assignment.dto.ToppingDto;
import com.bestseller.assignment.entity.ToppingEntity;
import com.bestseller.assignment.exception.ToppingNotFoundException;
import com.bestseller.assignment.mapper.ToppingMapper;
import com.bestseller.assignment.repository.ToppingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class ToppingService {

    private final ToppingRepository toppingRepository;
    private final ToppingMapper toppingMapper;

    public Optional<ToppingEntity> getTopping(UUID toppingId) {
        return toppingRepository.findByIdAndDeletedIsFalse(toppingId);
    }

    public ToppingDto create(ToppingDto topping) {
        ToppingEntity entity = new ToppingEntity(topping.id(), topping.name(), topping.price());
        entity = toppingRepository.save(entity);
        log.info("Topping {} created", entity.getId());
        return toppingMapper.toDto(entity);
    }

    @Transactional
    public ToppingDto update(UUID toppingId, ToppingDto toppingDto) {
        ToppingEntity entity = toppingRepository.findById(toppingId)
            .orElseThrow(ToppingNotFoundException::new);
        entity.setName(toppingDto.name());
        entity.setPrice(toppingDto.price());
        return toppingMapper.toDto(entity);
    }

    @Transactional
    public ToppingDto delete(UUID toppingId) {
        ToppingEntity entity = toppingRepository.findById(toppingId)
            .orElseThrow(ToppingNotFoundException::new);
        entity.setDeleted(true);
        log.info("Topping {} marked as deleted", toppingId);
        return toppingMapper.toDto(entity);
    }

    @Transactional
    public void increaseOrder(ToppingEntity topping) {
        topping.increaseOrder();
    }

    public List<ToppingEntity> getMostUsedToppings() {
        return toppingRepository.mostUsedToppings();
    }

}
