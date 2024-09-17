package com.bonappetit.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddRecipeDto {
    
    @Size(min = 2, max = 40, message = "Name length must be between 2 and 40 characters (inclusive of 2 and 40).")
    private String name;
    
    @Size(min = 2, max = 150, message = "Translation length must be between 2 and 150 characters (inclusive of 2 and 150).")
    private String ingredients;
    
    @NotBlank(message = "You must select a category")
    private String category;
}
