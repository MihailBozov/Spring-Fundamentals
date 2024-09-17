package com.philately.controller;

import com.philately.model.dto.StampDto;
import com.philately.service.StampService;
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
public class StampController {
    
    private final StampService stampService;

    @Autowired
    public StampController(StampService stampService) {
        this.stampService = stampService;
    }

    @GetMapping("/addStamp")
    public String addStamp() {
        return "add-stamp";
    }
    
    @ModelAttribute
    public StampDto stampDto() {
        return new StampDto();
    }
    
    @PostMapping("/addStamp")
    public String addStamp(@Valid StampDto stampDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("stampDto", stampDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.stampDto", bindingResult);
            
            return "redirect:/addStamp";
        }
        
        this.stampService.addStamp(stampDto);
        
        return "redirect:/home";
    }
    
    @GetMapping("/addToWishList/{id}")
    public String addToWishList(@PathVariable Long id) {
        this.stampService.addToWishList(id);
        return "redirect:/home";
    }
    
   @GetMapping("/stamps/removeFromWishList/{id}")
    public String removeFromWishList(@PathVariable Long id) {
        this.stampService.removeFromFavorites(id);
        return "redirect:/home";
   }   
   
   @GetMapping("/stamps/buyAll")
    public String buyAll() {
        this.stampService.buyAll();
        return "redirect:/home";
   }
}
