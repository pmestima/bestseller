package com.bestseller.assignment.service;

import com.bestseller.assignment.dto.CartInfoDto;
import com.bestseller.assignment.dto.FinalOrderDto;
import com.bestseller.assignment.dto.ProductDto;
import com.bestseller.assignment.entity.CartEntity;
import com.bestseller.assignment.entity.DrinkEntity;
import com.bestseller.assignment.entity.ProductEntity;
import com.bestseller.assignment.entity.ToppingEntity;
import com.bestseller.assignment.exception.CartNotFoundException;
import com.bestseller.assignment.exception.ProductNotFoundException;
import com.bestseller.assignment.mapper.CartInfoMapper;
import com.bestseller.assignment.repository.CartRepository;
import com.bestseller.assignment.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {

    private final DiscountFactory discountFactory;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartInfoMapper cartInfoMapper;
    private final ToppingService toppingService;
    private final DrinkService drinkService;

    @Transactional
    public CartInfoDto addToCart(int cartId, ProductDto productDto) {
        CartEntity cart = cartRepository.findByCartIdAndCompleteIsFalse(cartId)
            .orElse(new CartEntity(cartId));

        ProductEntity newProduct = toProductEntity(productDto)
            .orElseThrow(ProductNotFoundException::new);
        newProduct = productRepository.save(newProduct);

        cart.addProduct(newProduct);
        cartRepository.save(cart);

        return cartInfoMapper.toCartInfoDto(cart);
    }

    public Optional<ProductEntity> toProductEntity(ProductDto productDto) {
        DrinkEntity drinkEntity = drinkService.getDrink(productDto.drink().id())
            .orElse(null);

        if (drinkEntity == null) {
            return Optional.empty();
        }

        List<ToppingEntity> toppingEntities = productDto.toppings()
            .stream()
            .map(toppingDto -> toppingService.getTopping(toppingDto.id()).orElse(null))
            .toList();

        if (toppingEntities.stream().anyMatch(Objects::isNull)) {
            return Optional.empty();
        }

        double toppingsAmount = toppingEntities.stream()
            .mapToDouble(ToppingEntity::getPrice)
            .sum();

        return Optional.of(new ProductEntity(UUID.randomUUID(), drinkEntity, toppingEntities, toppingsAmount + drinkEntity.getPrice()));
    }

    @Transactional
    public FinalOrderDto makeOrder(int cartId) {
        CartEntity cart = cartRepository.findByCartIdAndCompleteIsFalse(cartId)
            .orElseThrow(CartNotFoundException::new);

        cart.getProducts()
            .stream()
            .map(ProductEntity::getToppings)
            .flatMap(Collection::stream)
            .forEach(toppingService::increaseOrder);

        double calculatedDiscount = discountFactory.getDiscountService(cart).calculateDiscount(cart);

        cart.setDiscountAmount(calculatedDiscount);
        cart.setComplete(true);

        log.info("Order {} created", cart.getCartId());
        return new FinalOrderDto(cart.getTotalAmount(), calculatedDiscount);
    }
}
