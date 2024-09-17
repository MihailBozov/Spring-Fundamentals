package com.softuni.battleships.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRegistrationDto {
    
    @NotBlank(message = "The username cannot be blank")
    @Size(min = 3, max = 10, message = "The size of the username should be between 3 and 10 characters!")
    private String username;
    
    @NotBlank(message = "The password cannot be blank")
    @Size(min = 5, max = 20, message = "The size of the fullName should be between 5 and 20 characters!")
    private String fullName;
    
    @NotBlank(message = "The email cannot be blank")
    private String email;
    
    @NotBlank(message = "The password cannot be blank")
    @Size(min = 3, message = "The size of the password should be more than 3 characters!")
    private String password;
    
    private String confirmPassword;
    
    public UserRegistrationDto() {
    }
    
    public UserRegistrationDto(String username, String fullName, String email, String password, String confirmPassword) {
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getFullName() {
        return fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getConfirmPassword() {
        return confirmPassword;
    }
    
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    
    @Override
    public String toString() {
        return "UserRegistrationDto{" +
                "username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
