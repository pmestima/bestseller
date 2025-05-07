package com.bestseller.assignment.config;

import com.bestseller.assignment.controller.AdminController;
import com.bestseller.assignment.dto.DrinkDto;
import com.bestseller.assignment.dto.ToppingDto;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.UUID;

//@Profile("test")
@Component
@RequiredArgsConstructor
public class DbInit {

    private final AdminController adminController;

    @PostConstruct
    private void postConstruct() {
        adminController.createDrink(new DrinkDto(UUID.fromString("ad6661bd-afdc-43c5-87d3-8e157d368f67"), "Black Coffee", 4.0));
        adminController.createDrink(new DrinkDto(UUID.fromString("08b4bf6b-4029-4563-8d65-51cd2807f393"), "Latte", 5.0));
        adminController.createDrink(new DrinkDto(UUID.fromString("405b76ed-4c6e-46b8-b063-e46680ddc8a5"), "Mocha", 6.0));
        adminController.createDrink(new DrinkDto(UUID.fromString("44b7f935-8ebd-4b36-a2dd-583483320178"), "Tea", 3.0));

        adminController.createTopping(new ToppingDto(UUID.fromString("9fdb059c-39fb-412f-a683-97a34289ebf2"), "Milk", 2.0));
        adminController.createTopping(new ToppingDto(UUID.fromString("1e25cadb-c7e8-4a57-88f2-6f994633a6e6"), "Hazelnut", 3.0));
        adminController.createTopping(new ToppingDto(UUID.fromString("5b703b4b-768f-49da-8146-173bac033d5a"), "Chocolate", 5.0));
        adminController.createTopping(new ToppingDto(UUID.fromString("74168ce1-786a-4d1d-b6f3-78bf6ddff342"), "Lemon", 2.0));
    }
}
