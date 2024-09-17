package com.bonappetit.model.entity;

import com.bonappetit.model.enums.CategoryName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryName name;

    @Basic
    private String description;

    @OneToMany(mappedBy = "category", targetEntity = Recipe.class)
    private List<Recipe> recipes;
    
    public Category(CategoryName name) {
        this.name = name;
    }
    
    public Category(CategoryName name, String description) {
        this.name = name;
        this.description = description;
    }
}
