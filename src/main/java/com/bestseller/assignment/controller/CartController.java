package com.bestseller.assignment.controller;

import com.bestseller.assignment.dto.CartInfoDto;
import com.bestseller.assignment.dto.FinalOrderDto;
import com.bestseller.assignment.dto.ProductDto;
import com.bestseller.assignment.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("cart")
public class CartController {

    private final CartService cartService;

    @Operation(summary = "Add a product to the cart", description = "Adds a drink and toppings to the specified cart")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Product added to cart"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "404", description = "Cart not found")
    })
    @PostMapping("{cartId}/products")
    public CartInfoDto addToCart(@PathVariable int cartId, @RequestBody @Validated ProductDto productDto) {
        return cartService.addToCart(cartId, productDto);
    }

    @Operation(summary = "Checkout a cart", description = "Creates a final order from the products in the cart")
    @PostMapping("{cartId}/checkout")
    public FinalOrderDto makeOrder(@PathVariable int cartId) {
        return cartService.makeOrder(cartId);
    }
}
