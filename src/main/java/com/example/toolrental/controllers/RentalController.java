package com.example.toolrental.controllers;

import com.example.toolrental.entities.Rental;
import com.example.toolrental.entities.RentalRequest;
import com.example.toolrental.services.RentalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/rentals")
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }
    @PostMapping
    public ResponseEntity<Rental> checkoutRental(@Valid @RequestBody RentalRequest request) {
        Rental rental = rentalService.saveRental(request);
        System.out.println(rental); // printing to console
        return new ResponseEntity<>(rental, HttpStatus.CREATED);
    }
}
