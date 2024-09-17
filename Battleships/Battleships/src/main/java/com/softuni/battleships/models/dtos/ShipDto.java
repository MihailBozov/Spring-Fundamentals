package com.softuni.battleships.models.dtos;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class ShipDto {
    
    @NotBlank
    @Size(min = 2, max = 10)
    private String name;
    
    
    @Positive
    private long health;
    
    @Positive
    private long power;
    
    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate created;
    
    @Positive
    int category;
    
    public ShipDto() {
    }
    
    public ShipDto(String name, long health, long power, LocalDate created, int category) {
        this.name = name;
        this.health = health;
        this.power = power;
        this.created = created;
        this.category = category;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public long getHealth() {
        return health;
    }
    
    public void setHealth(long health) {
        this.health = health;
    }
    
    public long getPower() {
        return power;
    }
    
    public void setPower(long power) {
        this.power = power;
    }
    
    public LocalDate getCreated() {
        return created;
    }
    
    public void setCreated(LocalDate created) {
        this.created = created;
    }
    
    public int getCategory() {
        return category;
    }
    
    public void setCategory(int category) {
        this.category = category;
    }
}
