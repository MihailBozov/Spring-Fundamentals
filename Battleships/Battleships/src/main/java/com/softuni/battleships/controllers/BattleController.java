package com.softuni.battleships.controllers;

import com.softuni.battleships.models.dtos.StartBattleDto;
import com.softuni.battleships.services.AuthService;
import com.softuni.battleships.services.BattleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BattleController {
    
    private AuthService authService;
    private BattleService battleService;
    
    @Autowired
    public BattleController(AuthService authService, BattleService battleService) {
        this.authService = authService;
        this.battleService = battleService;
    }
    
    @PostMapping("/battle")
    public String battle(@Valid StartBattleDto startBattleDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        
        if (!this.authService.isLogged()) {
            return "redirect:/";
        }
        
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("startBattleDto", startBattleDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.startBattleDto", bindingResult);
            return "redirect:/home";
        }
        
        this.battleService.attack(startBattleDto);
        return "redirect:/home";
    }
}
