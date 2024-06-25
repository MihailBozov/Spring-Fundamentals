package com.paintingscollectors.controller;

import com.paintingscollectors.model.dto.AddPaintingDto;
import com.paintingscollectors.service.PaintingService;
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
public class PaintingController {
    
    private UserSession userSession;
    private PaintingService paintingService;
    
    @Autowired
    public PaintingController(UserSession userSession, PaintingService paintingService) {
        this.userSession = userSession;
        this.paintingService = paintingService;
    }
    
    @GetMapping("/addPainting")
    public String addPainting() {
        if (!userSession.isLoggedIn()) {
            return "redirect:/login";
        }
        return "add-painting";
    }
    
    @ModelAttribute("addPaintingDto")
    public AddPaintingDto addPaintingDto() {
        return new AddPaintingDto();
    }
    
    @PostMapping("/addPainting") 
    public String addPainting(@Valid AddPaintingDto addPaintingDto,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        
        if (!userSession.isLoggedIn()) {
            return "redirect:/login";
        }
        
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addPaintingDto", addPaintingDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addPaintingDto", bindingResult);
            
            return "redirect:/addPainting";
        }
        
        this.paintingService.createPainting(addPaintingDto);
        
        return "redirect:/home";
    }
    
}
