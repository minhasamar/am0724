package com.example.toolrental.controllers;

import com.example.toolrental.entities.Rental;
import com.example.toolrental.entities.RentalRequest;
import com.example.toolrental.services.RentalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/api/v1/rentals")
@Validated
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }
    @PostMapping("checkout")
    public ResponseEntity<String> checkoutRental(@Valid @RequestBody RentalRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation failed");
        }
        Rental rental = rentalService.saveRental(request);
        System.out.println(rental); // printing to console
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
                        .path("/checkout")
                                .buildAndExpand(rental.getId())
                                        .toUri();
        //return ResponseEntity.created(location).body(rental);
        return new ResponseEntity<>("Agreement created", HttpStatus.CREATED);
    }
}
