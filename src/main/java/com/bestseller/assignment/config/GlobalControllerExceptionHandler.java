package com.bestseller.assignment.config;

import com.bestseller.assignment.exception.CartNotFoundException;
import com.bestseller.assignment.exception.DrinkNotFoundException;
import com.bestseller.assignment.exception.ProductNotFoundException;
import com.bestseller.assignment.exception.ToppingNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler({CartNotFoundException.class, DrinkNotFoundException.class, ToppingNotFoundException.class, ProductNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException() {
        return ResponseEntity.notFound().build();
    }

    // Any other exception should be handled by Spring
}
