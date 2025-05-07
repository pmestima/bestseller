package com.bestseller.assignment.mapper;

import com.bestseller.assignment.dto.CartInfoDto;
import com.bestseller.assignment.dto.ProductDto;
import com.bestseller.assignment.entity.CartEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CartInfoMapper {

    private final ProductMapper productMapper;

    public CartInfoDto toCartInfoDto(CartEntity cart) {
        List<ProductDto> productDtoList = cart.getProducts()
            .stream()
            .map(productMapper::toDto)
            .toList();

        return new CartInfoDto(cart.getCartId(), productDtoList, cart.getTotalAmount());
    }
}
