package com.example.toolrental.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class ToolCharge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tool_type", unique = true)
    @NotBlank(message = "Tool type is required")
    private String toolType;

    @PositiveOrZero(message = "Daily charge must be zero or greater")
    private Double dailyCharge;

    @Convert(converter = org.hibernate.type.YesNoConverter.class)
    private Boolean weekdayCharge;

    @Convert(converter = org.hibernate.type.YesNoConverter.class)
    private Boolean weekendCharge;

    @Convert(converter = org.hibernate.type.YesNoConverter.class)
    private Boolean holidayCharge;

    public ToolCharge() {

    }
    public ToolCharge (String toolType, Double dailyCharge, Boolean weekdayCharge, Boolean weekendCharge, Boolean holidayCharge) {
        this.toolType = toolType;
        this.dailyCharge = dailyCharge;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToolType() {
        return toolType;
    }

    public void setToolType(String toolType) {
        this.toolType = toolType;
    }

    public Double getDailyCharge() {
        return dailyCharge;
    }

    public void setDailyCharge(Double dailyCharge) {
        this.dailyCharge = dailyCharge;
    }

    public Boolean getWeekdayCharge() {
        return weekdayCharge;
    }

    public void setWeekdayCharge(Boolean weekdayCharge) {
        this.weekdayCharge = weekdayCharge;
    }

    public Boolean getWeekendCharge() {
        return weekendCharge;
    }

    public void setWeekendCharge(Boolean weekendCharge) {
        this.weekendCharge = weekendCharge;
    }

    public Boolean getHolidayCharge() {
        return holidayCharge;
    }

    public void setHolidayCharge(Boolean holidayCharge) {
        this.holidayCharge = holidayCharge;
    }

    @Override
    public String toString() {
        return "ToolCharge{" +
                "id=" + id +
                ", toolType='" + toolType + '\'' +
                ", dailyCharge=" + dailyCharge +
                ", weekdayCharge=" + weekdayCharge +
                ", weekendCharge=" + weekendCharge +
                ", holidayCharge=" + holidayCharge +
                '}';
    }
}
