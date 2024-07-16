package com.example.toolrental.entities;

import jakarta.validation.constraints.*;
import java.util.Date;

public class RentalRequest{
    @NotBlank(message = "Tool Code is required")
    public String toolCode;

    @NotNull(message = "Checkout date is required")
    public Date checkOutDate;

    @NotNull(message = "Rental days must be more than zero")
    @Positive(message = "Rental days must be more than zero")
    public Integer rentalDays;

    @NotNull
    @DecimalMin(value = "0", message = "Discount must be between 0 and 100")
    @DecimalMax(value = "100", message = "Discount must be between 0 and 100")
    public Double discountPercent;

    public RentalRequest(String toolCode, Date checkOutDate, Integer rentalDays, Double discountPercent) {
        this.toolCode = toolCode;
        this.checkOutDate = checkOutDate;
        this.rentalDays = rentalDays;
        this.discountPercent = discountPercent;
    }
}
