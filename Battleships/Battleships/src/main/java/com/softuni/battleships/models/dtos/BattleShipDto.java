package com.softuni.battleships.models.dtos;

import com.softuni.battleships.models.entities.Ship;

public class BattleShipDto {
    
    private long id;
    
    
    private  String name;
    
    private long health;
    
    
    private long power;
    
    public BattleShipDto() {
    }
    public BattleShipDto(Ship ship) {
        this.id = ship.getId();
        this.name = ship.getName();
        this.health = ship.getHealth();
        this.power = ship.getPower();
    }
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
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
}
