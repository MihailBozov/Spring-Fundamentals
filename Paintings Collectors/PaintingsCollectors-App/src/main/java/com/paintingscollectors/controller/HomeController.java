package com.paintingscollectors.controller;

import com.paintingscollectors.service.PaintingService;
import com.paintingscollectors.service.UserService;
import com.paintingscollectors.session.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
    private UserSession userSession;
    private UserService userService;
    private PaintingService paintingService;
    
    @Autowired
    public HomeController(UserSession userSession, UserService userService, PaintingService paintingService) {
        this.userSession = userSession;
        this.userService = userService;
        this.paintingService = paintingService;
    }
    
    @ModelAttribute
    public void addAttributes(Model model) {
        if (userSession.isLoggedIn()) {
            model.addAttribute("addedPaintings", this.paintingService.getAddedPaintings());
            model.addAttribute("favoritePaintings", this.paintingService.getFavoritePaintings());
            model.addAttribute("otherPaintings", this.paintingService.getOtherPaintings());
            model.addAttribute("mostRatedPaintings", this.paintingService.getMostRatedPaintings());
        }
    }
    
    @GetMapping("/")
    public String index() {
        if (this.userSession.isLoggedIn()) {
            return "redirect:/home";
        }
        return "index";
    }
    
    @GetMapping("/home")
    public String home() {
        if (!this.userSession.isLoggedIn()) {
            return "redirect:/";
        }
        return "home";
    }
    
    @DeleteMapping("/deletePainting/{id}")
    public String deletePainting(@PathVariable Long id) {
        boolean isDeleted = this.paintingService.deletePainting(id);
        return "redirect:/home";
    }
    
    @DeleteMapping("/removeFromFavorites/{id}")
    public String removeFromFavorites(@PathVariable Long id) {
        boolean isRemoved = this.paintingService.removeFromFavorite(id);
        return "redirect:/home";
    }
    
    @PostMapping("/addToFavorites/{id}")
    public String addToFavorite(@PathVariable Long id) {
        boolean addToFavorite = this.paintingService.addToFavorite(id);
        return "redirect:/home";
    }
    
    
    @PostMapping("/doVote/{id}")
    public String doVote(@PathVariable Long id) {
        long votes = this.paintingService.vote(id);
        return "redirect:/home";
    }
    
}
