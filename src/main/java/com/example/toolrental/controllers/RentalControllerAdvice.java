package com.example.toolrental.controllers;

import com.example.toolrental.exceptions.RentalRequestInvalidDiscountException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RentalControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RentalRequestInvalidDiscountException.class)
    public ProblemDetail handleInvalidDiscount(RentalRequestInvalidDiscountException ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

}
