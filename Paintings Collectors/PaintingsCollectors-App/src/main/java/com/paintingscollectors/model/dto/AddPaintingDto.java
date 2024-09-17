package com.paintingscollectors.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Getter
@Setter
@NoArgsConstructor
public class AddPaintingDto {
    
    @NotBlank(message = "The name cannot be empty")
    @Size(min = 5, max = 40, message = "Name length must be between 5 and 40 characters ")
    private String name;
    
    @NotBlank(message = "The author cannot be empty")
    @Size(min = 5, max = 30, message = "Author length must be between 5 and 30 characters")
    private String author;
    
    @NotBlank(message = "The imageUrl cannot be empty")
    @Size(max = 150, message = "The url cannot more than 150 characters")
    private String imageUrl;
    
    @NotBlank(message = "You must select a style")
    private String style;

}
