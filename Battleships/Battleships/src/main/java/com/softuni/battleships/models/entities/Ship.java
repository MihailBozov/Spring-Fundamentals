package com.softuni.battleships.models.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "ships")
public class Ship {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    @Column(nullable = false)
    private long health;
    
    @Column(nullable = false)
    private long power;
    
    @Column(nullable = false)
    private LocalDate created;
    
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;
    
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    
    public Ship() {}
    
    public Ship(String name, long health, long power, LocalDate created, Category category, User user) {
        this.name = name;
        this.health = health;
        this.power = power;
        this.created = created;
        this.category = category;
        this.user = user;
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
    
    public Category getCategory() {
        return category;
    }
    
    public void setCategory(Category category) {
        this.category = category;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public long getId() {
        return id;
    }
}
