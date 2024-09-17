package com.philately.model.dto;

import com.philately.model.entity.Paper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StampDto {
    
    @Size(min = 5, max = 20, message = "Name length must be between 5 and 20 characters!")
    private String name;
    
    @Size(min = 5, max = 25, message = "Description length must be between 5 and 25 characters!")
    private String description;
    
    @NotBlank(message = "You must select a type of paper!")
    private String paper;
    
    @Size(max = 150, message = "Please enter a valid image URL!")
    @NotBlank(message = "Please enter a valid image URL!")
    private String imageUrl;
    
    @Positive(message = "must be grater than 0")
    private int price;
    
}
