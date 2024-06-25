package com.paintingscollectors.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public String getStyle() {
        return style;
    }
    
    public void setStyle(String style) {
        this.style = style;
    }
}
