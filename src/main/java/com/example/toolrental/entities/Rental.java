package com.example.toolrental.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Rental {

    @Id
    @SequenceGenerator(
            name = "rental_seq",
            sequenceName = "rental_seq",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rental_seq")
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
    private Date checkOutDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
    private Date dueDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tool_code", referencedColumnName = "tool_code")
    @NotBlank(message = "Tool selection is required")
    private Tool tool;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tool_type", referencedColumnName = "tool_type")
    @NotBlank(message = "Tool charge info is required")
    private ToolCharge toolCharge;

    @Positive(message = "Rental days should be more than zero")
    private Integer rentalDays;

    @Positive(message = "Charge days should be more than zero")
    private Integer chargeDays;

    @Positive(message = "Pre-discount amount should be more than zero")
    private Double preDiscountCharge;

    @PositiveOrZero(message = "Discount should be zero or more")
    private Double discountPercent;

    @PositiveOrZero(message = "Discount Amount should be zero or more")
    private Double discountAmount;

    @Positive(message = "Final Charge should be more than zero")
    private Double finalCharge;

    public Rental(Long id, Date checkOutDate, Date dueDate, Tool tool, ToolCharge toolCharge, Integer rentalDays, Integer chargeDays, Double preDiscountCharge, Double discountPercent, Double discountAmount, Double finalCharge) {
        this.id = id;
        this.checkOutDate = checkOutDate;
        this.dueDate = dueDate;
        this.tool = tool;
        this.toolCharge = toolCharge;
        this.rentalDays = rentalDays;
        this.chargeDays = chargeDays;
        this.preDiscountCharge = preDiscountCharge;
        this.discountPercent = discountPercent;
        this.discountAmount = discountAmount;
        this.finalCharge = finalCharge;
    }

    public Rental() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public @NotBlank(message = "Tool selection is required") Tool getTool() {
        return tool;
    }

    public void setTool(@NotBlank(message = "Tool selection is required") Tool tool) {
        this.tool = tool;
    }

    public @NotBlank(message = "Tool charge info is required") ToolCharge getToolCharge() {
        return toolCharge;
    }

    public void setToolCharge(@NotBlank(message = "Tool charge info is required") ToolCharge toolCharge) {
        this.toolCharge = toolCharge;
    }

    public @Positive(message = "Rental days should be more than zero") Integer getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(@Positive(message = "Rental days should be more than zero") Integer rentalDays) {
        this.rentalDays = rentalDays;
    }

    public @Positive(message = "Charge days should be more than zero") Integer getChargeDays() {
        return chargeDays;
    }

    public void setChargeDays(@Positive(message = "Charge days should be more than zero") Integer chargeDays) {
        this.chargeDays = chargeDays;
    }

    public @Positive(message = "Pre-discount amount should be more than zero") Double getPreDiscountCharge() {
        return preDiscountCharge;
    }

    public void setPreDiscountCharge(@Positive(message = "Pre-discount amount should be more than zero") Double preDiscountCharge) {
        this.preDiscountCharge = preDiscountCharge;
    }

    public @PositiveOrZero(message = "Discount should be zero or more") Double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(@PositiveOrZero(message = "Discount should be zero or more") Double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public @PositiveOrZero(message = "Discount Amount should be zero or more") Double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(@PositiveOrZero(message = "Discount Amount should be zero or more") Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public @Positive(message = "Final Charge should be more than zero") Double getFinalCharge() {
        return finalCharge;
    }

    public void setFinalCharge(@Positive(message = "Final Charge should be more than zero") Double finalCharge) {
        this.finalCharge = finalCharge;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return  " Tool Code: " + tool.getToolCode() + "\n" +
                " Tool Type: " + toolCharge.getToolType() + "\n" +
                " Tool Brand: " + tool.getBrand() + "\n" +
                " CheckOut Date: " + dateFormat.format(checkOutDate) + "\n" +
                " Due Date: " + dateFormat.format(dueDate) + "\n" +
                " Rental Days: " + rentalDays + "\n" +
                " Charge Days: " + chargeDays + "\n" +
                " PreDiscountCharge: $" + preDiscountCharge + "\n" +
                " Discount Percent: " + discountPercent +"%"+ "\n" +
                " Discount Amount: $" + discountAmount + "\n" +
                " Final Charge: $" + finalCharge;
    }
}
