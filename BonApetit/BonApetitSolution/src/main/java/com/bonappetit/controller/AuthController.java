package com.bonappetit.controller;

import com.bonappetit.model.dto.LoginDto;
import com.bonappetit.model.dto.RegisterDto;
import com.bonappetit.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {
    
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }
    
//    -----     Login     -----
    
    @GetMapping("/login")
    public String login() {

        if (this.userService.isLoggedIn()) {
            return "redirect:/";
        }
        
        return "login";
    }

    @ModelAttribute
    public LoginDto loginDto() {
        return new LoginDto();
    }

    @PostMapping("/login")
    public String login(@Valid LoginDto loginDto,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        if (this.userService.isLoggedIn()) {
            return "redirect:/";
        }
        
        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("loginDto", loginDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginDto", bindingResult);

            return "redirect:/login";
        }

        this.userService.login(loginDto);
        return "redirect:/";
    }
    

//    -----     Register     -----
    

    @GetMapping("/logout") 
    public String logout() {

        if (!this.userService.isLoggedIn()) {
            return "redirect:/";
        }
        
        this.userService.logout();
        return "redirect:/";
    }
    

//    -----     Register     -----

    @GetMapping("/register")
    public String register() {

        if (this.userService.isLoggedIn()) {
            return "redirect:/";
        }
        
        return "register";
    }

    @ModelAttribute
    public RegisterDto registerDto() {
        return new RegisterDto();
    }

    @PostMapping("/register")
    public String register(@Valid RegisterDto registerDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        
        if (userService.isLoggedIn()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerDto", registerDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerDto", bindingResult);


            return "redirect:/register";
        }

        this.userService.register(registerDto);
        return "redirect:/";
    }
}
