package com.example.toolrental.services;

import com.example.toolrental.entities.Rental;
import com.example.toolrental.entities.RentalRequest;

public interface RentalService {
    Rental saveRental(RentalRequest request);
}
