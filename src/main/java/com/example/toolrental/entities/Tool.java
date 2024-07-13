package com.example.toolrental.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

@Entity
public class Tool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tool_code", unique = true)
    @NotBlank(message = "Code is required and should be unique")
    private String toolCode;

    @Column(name = "tool_type")
    @NotBlank(message = "Tool type is required")
    private String toolType;

    private String brand;

    public Tool () {

    }

    public Tool(String toolCode, String toolType, String brand) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.brand = brand;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToolCode() {
        return toolCode;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public String getToolType() {
        return toolType;
    }

    public void setToolType(String toolType) {
        this.toolType = toolType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Tool{" +
                "id=" + id +
                ", toolCode='" + toolCode + '\'' +
                ", toolType='" + toolType + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
