package com.philately.controller;

import com.philately.model.entity.Stamp;
import com.philately.service.StampService;
import com.philately.util.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class HomeController {

    private final UserSession userSession;
    private final StampService stampService;

    @Autowired
    public HomeController(UserSession userSession, StampService stampService) {
        this.userSession = userSession;
        this.stampService = stampService;
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

    @ModelAttribute(name = "myStamps")
    public List<Stamp> myStamps() {
        return this.stampService.getMyStamps();
    }
    
    @ModelAttribute(name = "offeredStamps")
    public List<Stamp> offeredStamps() {
        return this.stampService.getForeignStamps();
    }
    
    @ModelAttribute(name = "wishedList")
    public List<Stamp> wishedList() {
        return this.stampService.getWishedStamps();
    }
    
    @ModelAttribute(name = "purchasedStamps")
    public List<Stamp> purchasedStamps() {
        return this.stampService.getAllPurchasedStamps();
    }
}
