package com.example.toolrental.dao;

import com.example.toolrental.entities.ToolCharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ToolChargeRepository extends JpaRepository<ToolCharge, String> {
    @Query(
            "SELECT TC from ToolCharge TC WHERE TC.toolType = ?1"
    )
    Optional<ToolCharge> findByToolType(String toolType);
}
