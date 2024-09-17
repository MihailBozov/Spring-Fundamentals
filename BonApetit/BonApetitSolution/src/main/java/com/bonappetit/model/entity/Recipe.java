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
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Basic
    private String ingredients;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;
    
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User addedBy;
    
    @ManyToMany(mappedBy = "favoriteRecipes", targetEntity = User.class)
    private List<Recipe> favoriteRecipes;
}
