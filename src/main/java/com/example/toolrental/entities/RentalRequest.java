package com.example.toolrental.entities;

import java.util.Date;

public record RentalRequest (
    String toolCode,
    Date checkOutDate,
    Integer rentalDays,
    Double discountPercent
    ){
}
