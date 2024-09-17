package com.softuni.battleships.controllers;

import com.softuni.battleships.models.dtos.BattleShipDto;
import com.softuni.battleships.models.dtos.StartBattleDto;
import com.softuni.battleships.services.AuthService;
import com.softuni.battleships.services.ShipService;
import com.softuni.battleships.sessions.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class HomeController {
    private ShipService shipService;
    
    private AuthService authService;
    
    @ModelAttribute("startBattleDto")
    public StartBattleDto initStartBattleDto() {
        return new StartBattleDto();
    }
    
    @Autowired
    public HomeController(ShipService shipService, AuthService authService) {
        this.shipService = shipService;
        this.authService = authService;
    }
    
    @GetMapping("/")
    public String loggedOutIndex() {
        return "/index";
    }
    
    @GetMapping("/home")
    public String loggedInIndex(Model model) {
        if (!this.authService.isLogged()) {
            return "redirect:/";
        }
        
        long ownerId = this.authService.getLoggedUserId();
       
        List<BattleShipDto> ownShips = this.shipService.getShipsOwnedBy(ownerId);
        List<BattleShipDto> enemyShips = this.shipService.getShipsNotOwnedBy(ownerId);
        List<BattleShipDto> sortedShips = this.shipService.getAllSorted();
        
        model.addAttribute("ownShips", ownShips);
        model.addAttribute("enemyShips", enemyShips);
        model.addAttribute("storedShips", sortedShips);
        
        return "/home";
    }
}
