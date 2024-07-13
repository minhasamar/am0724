package com.example.toolrental.dao;

import com.example.toolrental.entities.Tool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ToolRepository extends JpaRepository<Tool, String> {

    @Query("SELECT T from Tool T WHERE T.toolCode = ?1")
    Optional<Tool> findByToolCode(String toolCode);
}
