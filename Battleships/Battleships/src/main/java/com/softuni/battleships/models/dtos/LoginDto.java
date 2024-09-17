package com.softuni.battleships.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginDto {
    
    @NotBlank(message = "Your username cannot be blank")
    @Size(min = 3, max = 10, message = "Your username length should be between 3 and 10 characters")
    private String username;
    
    @NotBlank(message = "Your password cannot be blank")
    @Size(min = 3, message = "Your password length should be greater than 3 characters")
    private String password;
    
    public LoginDto() {
    }
    
    public LoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}
