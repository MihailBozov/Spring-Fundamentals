package com.softuni.battleships.models.entities;

import com.softuni.battleships.models.entities.enums.ShipType;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.ORDINAL)
    private ShipType name;
    
    @Column(columnDefinition = "TEXT", nullable = true)
    private String description;
    
    @OneToMany(mappedBy = "category", targetEntity = Ship.class)
    List<Ship> ships;
    
    public Category() {}
    
    public Category(ShipType name) {
        this.name = name;
    }
    
   
    public ShipType getName() {
        return name;
    }
    
    public void setName(ShipType name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public long getId() {
        return id;
    }
}
