package com.bonappetit.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String username;
    
    @Column(nullable = false)
    private String password;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @OneToMany(mappedBy = "addedBy", targetEntity = Recipe.class)
    private List<Recipe> addedRecipes;
    
    @ManyToMany
    @JoinTable(name = "users_favorites",
    joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "recipe_id", referencedColumnName = "id"))
    private List<Recipe> favoriteRecipes;
    
}
