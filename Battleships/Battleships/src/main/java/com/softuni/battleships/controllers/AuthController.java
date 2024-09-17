package com.softuni.battleships.controllers;

import com.softuni.battleships.models.dtos.LoginDto;
import com.softuni.battleships.models.dtos.UserRegistrationDto;
import com.softuni.battleships.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {
    
    private AuthService authService;
    
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    
    @ModelAttribute("registrationDto")
    public UserRegistrationDto initUserRegistrationDto() {
        return new UserRegistrationDto();
    }
    
    @ModelAttribute("loginDto")
    public LoginDto initLoginDto() {
        return new LoginDto();
    }
    
    
    @GetMapping("/register")
    public String register() {
        
        if (this.authService.isLogged()) {
            return "redirect:/home";
        }
        
        return "register";
    }
    
    @PostMapping("/register")
    public String register(@Valid UserRegistrationDto registrationDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        
        if (this.authService.isLogged()) {
            return "redirect:/home";
        }
        
        if (bindingResult.hasErrors() || !this.authService.register(registrationDto)) {
            redirectAttributes.addFlashAttribute("registrationDto", registrationDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registrationDto", bindingResult);
            return "redirect:/register";
        }
        
        return "redirect:/login";
    }
    
    @GetMapping("/login")
    public String login() {
        
        if (this.authService.isLogged()) {
            return "redirect:/home";
        }
        
        return "login";
    }
    
    @PostMapping("/login")
    public String login(@Valid LoginDto loginDto,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {
        if (this.authService.isLogged()) {
            return "redirect:/home";
        }
        
        if (bindingResult.hasErrors() || !this.authService.login(loginDto)) {
            redirectAttributes.addFlashAttribute("loginDto", loginDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginDto", bindingResult);
            redirectAttributes.addFlashAttribute("badCredentials", true);
            return "redirect:/login";
        }
        
        return "redirect:/home";
    }
    
    @GetMapping("/logout")
    public String logout() {
        this.authService.logout();
        return "index";
    }
    
    
}
