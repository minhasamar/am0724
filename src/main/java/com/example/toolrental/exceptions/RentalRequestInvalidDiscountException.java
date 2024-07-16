package com.example.toolrental.exceptions;

public class RentalRequestInvalidDiscountException extends RuntimeException {

    public RentalRequestInvalidDiscountException() {
        this("Discount percent should be between 0 and 100");
    }

    public RentalRequestInvalidDiscountException(String message) {
        super(message);
    }

    public RentalRequestInvalidDiscountException(double percent) {
        this("Discount percent should be between 0 and 100, but was " + percent);
    }
}
