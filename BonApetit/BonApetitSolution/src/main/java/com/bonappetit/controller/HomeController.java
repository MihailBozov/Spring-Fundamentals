package com.bonappetit.controller;

import com.bonappetit.model.entity.Recipe;
import com.bonappetit.service.RecipeService;
import com.bonappetit.util.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class HomeController {

    private final UserSession userSession;
    private final RecipeService recipeService;

    @Autowired
    public HomeController(UserSession userSession, RecipeService recipeService) {
        this.userSession = userSession;
        this.recipeService = recipeService;
    }
    
    @ModelAttribute(name = "desertRecipes")
    public List<Recipe> getDesertRecipes() {
        return this.recipeService.getAllDeserts();
    }
    
    @ModelAttribute(name= "mainDishes")
    public List<Recipe> getMainDishes() {
        return this.recipeService.getAllMainDishes();
    }
    
    @ModelAttribute(name = "cocktailRecipes")
    public List<Recipe> getCocktails() {
        return this.recipeService.getAllCocktails();
    }
    
    @ModelAttribute(name = "favoriteRecipes")
    public List<Recipe> getFavorites() {
        return this.recipeService.getAllFavorites();
    }

    @GetMapping("/")
    public String index() {

        if (!userSession.isLoggedIn()) {
            return "index";
        }
        return "redirect:/home";
    }
    
    @GetMapping("/home") 
    public String home() {
        if (userSession.isLoggedIn()) {
            return "home";
        }
        return "redirect:/";
    }


}
