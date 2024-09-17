package com.bonappetit.service;

import com.bonappetit.model.dto.AddRecipeDto;
import com.bonappetit.model.entity.Category;
import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.entity.User;
import com.bonappetit.model.enums.CategoryName;
import com.bonappetit.repo.CategoryRepository;
import com.bonappetit.repo.RecipeRepository;
import com.bonappetit.repo.UserRepository;
import com.bonappetit.util.UserSession;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {
    
    private final RecipeRepository recipeRepository;
    private final ModelMapper modelMapper;
    private final UserSession userSession;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository, ModelMapper modelMapper, UserSession userSession, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.recipeRepository = recipeRepository;
        this.modelMapper = modelMapper;
        this.userSession = userSession;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }
    
    public List<Recipe> getAllDeserts() {
        return this.recipeRepository.findAllDessertRecipes();
    }
    
    public List<Recipe> getAllMainDishes() {
        return this.recipeRepository.findAllMainDishRecipes();
    }
    
    public List<Recipe> getAllCocktails() {
        return this.recipeRepository.findAllCocktailRecipes();
    }
    
    public List<Recipe> getAllFavorites() {
        return this.recipeRepository.findAllFavoriteRecipes();
    }

    public void createNewRecipe(AddRecipeDto addRecipeDto) {
        Recipe recipe = this.modelMapper.map(addRecipeDto, Recipe.class);
        Category category = this.categoryRepository.findCategoryByName(CategoryName.valueOf(addRecipeDto.getCategory()));
        User user = this.userRepository.findById(this.userSession.getUser().getId()).get();
        recipe.setCategory(category);
        recipe.setAddedBy(user);
        this.recipeRepository.saveAndFlush(recipe);
    }
    
    @Transactional
    public void addToFavorites(Long id) {
        Optional<User> user = this.userRepository.findById(this.userSession.getId());
        Optional<Recipe> recipe = this.recipeRepository.findById(id);
        
        List<Recipe> favorites = this.recipeRepository.findAllFavoriteRecipes();
        
        if (recipe.isPresent() && user.isPresent()) {
            
            if (!favorites.contains(recipe.get())) {
                user.get().getFavoriteRecipes().add(recipe.get());
                this.userRepository.saveAndFlush(user.get());
            }
        }
    }
}
