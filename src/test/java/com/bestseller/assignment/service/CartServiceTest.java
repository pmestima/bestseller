package com.bestseller.assignment.service;

import com.bestseller.assignment.dto.CartInfoDto;
import com.bestseller.assignment.dto.DrinkDto;
import com.bestseller.assignment.dto.ProductDto;
import com.bestseller.assignment.dto.ToppingDto;
import com.bestseller.assignment.entity.CartEntity;
import com.bestseller.assignment.entity.DrinkEntity;
import com.bestseller.assignment.entity.ProductEntity;
import com.bestseller.assignment.entity.ToppingEntity;
import com.bestseller.assignment.mapper.CartInfoMapper;
import com.bestseller.assignment.repository.CartRepository;
import com.bestseller.assignment.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    private CartRepository cartRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private CartInfoMapper cartInfoMapper;
    @Mock
    private DrinkService drinkService;
    @Mock
    private ToppingService toppingService;
    @InjectMocks
    private CartService cartService;

    @Test
    void shouldAddToCartTest() {
        int cartId = 1;
        ProductDto product = createProduct();

        ProductEntity productEntity = createProductEntity(product);
        when(cartRepository.findByCartIdAndCompleteIsFalse(1)).thenReturn(Optional.empty());
        when(productRepository.save(any(ProductEntity.class))).thenReturn(productEntity);
        when(drinkService.getDrink(product.drink().id())).thenReturn(Optional.of(productEntity.getDrink()));
        when(toppingService.getTopping(productEntity.getToppings().get(0).getId())).thenReturn(Optional.of(productEntity.getToppings().get(0)));
        when(cartInfoMapper.toCartInfoDto(any(CartEntity.class))).thenReturn(new CartInfoDto(1, List.of(product), 5.0));

        CartInfoDto cartInfoDto = cartService.addToCart(cartId, product);

        verify(cartRepository, times(1)).save(any(CartEntity.class));
        assertEquals(1, cartInfoDto.cartId());
        assertEquals(5.0, cartInfoDto.totalAmount());
        assertEquals(1, cartInfoDto.products().size());
        ProductDto productDto = cartInfoDto.products().get(0);
        assertEquals(product.drink().id(), productDto.drink().id());
        assertEquals(product.toppings().get(0).id(), productDto.toppings().get(0).id());
    }

    private ProductEntity createProductEntity(ProductDto product) {
        DrinkEntity drink = new DrinkEntity(product.drink().id(), "drink", 5.0);
        ToppingEntity topping = new ToppingEntity(product.toppings().get(0).id(), "topping", 3.0);
        return new ProductEntity(UUID.randomUUID(), drink, List.of(topping), 8.0);
    }

    private ProductDto createProduct() {
        DrinkDto drink = new DrinkDto(UUID.randomUUID(), "", 0);
        ToppingDto topping = new ToppingDto(UUID.randomUUID(), "", 0);
        return new ProductDto(drink, List.of(topping));
    }
}
