package com.bonappetit.repo;

import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.enums.CategoryName;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    
    @Transactional
    @Query("SELECT r FROM Recipe AS r WHERE r.category.name = 'DESSERT'")
    List<Recipe> findAllDessertRecipes();
    
    @Transactional
    @Query("SELECT r FROM Recipe AS r where r.category.name = 'MAIN_DISH'")
    List<Recipe> findAllMainDishRecipes();
    
    @Transactional
    @Query("SELECT r FROM Recipe r WHERE r.category.name = 'COCKTAIL'")
    List<Recipe> findAllCocktailRecipes();
    
    @Transactional
    @Query("SELECT r FROM Recipe r WHERE r.favoriteRecipes IS NOT EMPTY ")
    List<Recipe> findAllFavoriteRecipes();
}
