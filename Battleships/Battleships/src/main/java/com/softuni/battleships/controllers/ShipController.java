package com.softuni.battleships.controllers;

import com.softuni.battleships.models.dtos.ShipDto;
import com.softuni.battleships.services.AuthService;
import com.softuni.battleships.services.ShipService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ShipController {
    
    private ShipService shipService;
    private AuthService authService;
    
    @Autowired
    public ShipController(ShipService shipService, AuthService authService) {
        this.shipService = shipService;
        this.authService = authService;
    }
    
    @ModelAttribute("shipDto")
    public ShipDto initShipDto() {
        return new ShipDto();
    }
    
    @GetMapping("/ship-add")
    public String ship() {
        if (!this.authService.isLogged()) {
            return "redirect:/";
        }
        return "ship-add";
    }
    
    @PostMapping("/ship-add")
    public String ship(@Valid ShipDto shipDto,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {
        
        if (!this.authService.isLogged()) {
            return "redirect:/";
        }
        
        if (bindingResult.hasErrors() || !this.shipService.create(shipDto)) {
            redirectAttributes.addFlashAttribute("shipDto", shipDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.shipDto", bindingResult);
            
            return "redirect:/ship-add";
        }
        
        return "redirect:/home";
    }
}
