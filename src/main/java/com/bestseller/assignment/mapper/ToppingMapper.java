package com.bestseller.assignment.mapper;

import com.bestseller.assignment.dto.ToppingDto;
import com.bestseller.assignment.entity.ToppingEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ToppingMapper {

    public ToppingDto toDto(ToppingEntity toppingEntity) {
        return new ToppingDto(toppingEntity.getId(), toppingEntity.getName(), toppingEntity.getPrice());
    }
}
