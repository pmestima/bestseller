package com.bestseller.assignment.controller;

import com.bestseller.assignment.dto.DrinkDto;
import com.bestseller.assignment.dto.ToppingDto;
import com.bestseller.assignment.mapper.ToppingMapper;
import com.bestseller.assignment.service.DrinkService;
import com.bestseller.assignment.service.ToppingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {

    private final DrinkService drinkService;
    private final ToppingService toppingService;
    private final ToppingMapper toppingMapper;

    @GetMapping("most-used-toppings")
    public List<ToppingDto> getMostUsedToppings() {
        return toppingService.getMostUsedToppings()
            .stream()
            .map(toppingMapper::toDto)
            .toList();
    }

    // Drinks endpoints

    @PostMapping("drinks")
    @ResponseStatus(HttpStatus.CREATED)
    public DrinkDto createDrink(@RequestBody DrinkDto drink) {
        return drinkService.create(drink);
    }

    @PutMapping("drinks/{drinkId}")
    public DrinkDto updateDrink(@PathVariable UUID drinkId, @RequestBody DrinkDto drinkDto) {
        return drinkService.update(drinkId, drinkDto);
    }

    @DeleteMapping("drinks/{drinkId}")
    public ResponseEntity<Void> deleteDrink(@PathVariable UUID drinkId) {
        drinkService.delete(drinkId);
        return ResponseEntity.noContent().build();
    }

    // Toppings endpoints

    @PostMapping("toppings")
    @ResponseStatus(HttpStatus.CREATED)
    public ToppingDto createTopping(@RequestBody ToppingDto topping) {
        return toppingService.create(topping);
    }

    @PutMapping("toppings/{toppingId}")
    public ToppingDto updateTopping(@PathVariable UUID toppingId, @RequestBody ToppingDto toppingDto) {
        return toppingService.update(toppingId, toppingDto);
    }

    @DeleteMapping("toppings/{toppingId}")
    public ResponseEntity<Void> deleteTopping(@PathVariable UUID toppingId) {
        toppingService.delete(toppingId);
        return ResponseEntity.noContent().build();
    }

}
