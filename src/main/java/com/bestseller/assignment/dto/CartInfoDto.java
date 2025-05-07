package com.bestseller.assignment.dto;

import java.util.List;

public record CartInfoDto(
    int cartId,
    List<ProductDto> products,
    double totalAmount) {
}
