package com.example.toolrental.dao;

import com.example.toolrental.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository  extends JpaRepository<Rental,Integer> {

}
