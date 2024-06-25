package com.paintingscollectors.controller;

import com.paintingscollectors.model.dto.UserLoginDto;
import com.paintingscollectors.model.dto.UserRegisterDto;
import com.paintingscollectors.service.UserService;
import com.paintingscollectors.session.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AuthController {
    
    private UserService userService;
    private UserSession userSession;
    
    @Autowired
    public AuthController(UserService userService, UserSession userSession) {
        this.userService = userService;
        this.userSession = userSession;
    }
    
    @GetMapping("/register")
    public String register() {
        if (userSession.isLoggedIn()) {
            return "redirect:/home";
        }
        
        return "register";
    }
    
    @ModelAttribute("registerDto")
    public UserRegisterDto initRegisterDto() {
        return new UserRegisterDto();
    }
    
    
    
    @PostMapping("/register")
    public String register(@Valid UserRegisterDto registerDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        
        if(userSession.isLoggedIn()) {
            return "redirect:/home";
        }
        
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerDto", registerDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerDto", bindingResult);
            return "redirect:/register";
        }
        
        this.userService.register(registerDto);
        
        return "redirect:/";
    }
    
//    -------------       login       --------------
    
    
    
    @ModelAttribute("loginDto")
    public UserLoginDto initLoginDto() {
        return new UserLoginDto();
    }
    
    @GetMapping("/login")
    public String login() {
        
        if(userSession.isLoggedIn()) {
            return "redirect:/home";
        }
        
        return "login";
    }
    
    @PostMapping("/login")
    public String login(@Valid UserLoginDto loginDto, 
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {
        
        if(userSession.isLoggedIn()) {
            return "redirect:/home";
        }
        
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginDto", loginDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginDto", bindingResult);
            return "redirect:/login";
        }
        
        this.userService.login(loginDto);
        
        return "redirect:/home";
    }
    
    
    //    -------------       login       --------------
    
    
    @GetMapping("/logout")
    public String logout() {
        if(!userSession.isLoggedIn()) {
            return "redirect:/";
        }
        
        this.userService.logout();
        return "index";
    }
}















