package com.bonappetit.controller;

import com.bonappetit.model.dto.AddRecipeDto;
import com.bonappetit.service.RecipeService;
import com.bonappetit.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RecipeController {
    
    private final RecipeService recipeService;
    private final UserService userService;

    @Autowired
    public RecipeController(RecipeService recipeService, UserService userService) {
        this.recipeService = recipeService;
        this.userService = userService;
    }

    @ModelAttribute
    public AddRecipeDto addRecipeDto() {
        return new AddRecipeDto();
    }
    
    @GetMapping("/addRecipe") 
    public String addRecipe() {
        
        if (!this.userService.isLoggedIn()) {
            return "redirect:/";
        }
        
        return "recipe-add";
    }

    @PostMapping("/addRecipe")
    public String addRecipe(@Valid AddRecipeDto addRecipeDto,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {
        if (!this.userService.isLoggedIn()) {
            return "redirect:/";
        }
        
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("recipeDto", addRecipeDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.recipeDto", bindingResult);
            
            return "redirect:/addRecipe";
        }
        
        this.recipeService.createNewRecipe(addRecipeDto);
        
        return "redirect:/home";
    }
    
    @PostMapping("/addToFavorites/{id}")
    public String addToFavorites(@PathVariable Long id) {
        this.recipeService.addToFavorites(id);
        return "redirect:/home";
    }
}
