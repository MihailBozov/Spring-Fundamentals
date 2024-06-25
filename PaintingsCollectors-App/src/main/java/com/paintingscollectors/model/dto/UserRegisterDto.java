package com.paintingscollectors.model.dto;

import com.paintingscollectors.vallidation.UniqueUserName;
import com.paintingscollectors.vallidation.MatchingRegisterPasswords;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@MatchingRegisterPasswords(message = "Oops, it looks like the passwords do not match")
public class UserRegisterDto {
    
    @NotBlank(message = "Oops, your username cannot be blank")
    @UniqueUserName(message = "Oops, that username is already taken")
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters")
    private String username;
    
    @NotBlank(message = "Email cannot be empty")
    @Pattern(regexp = "^\\S+@\\S+$", message = "Oops, that does not look like a valid email")
    private String email;
    
    @NotBlank(message = "Oops, your password cannot be blank")
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters")
    private String password;
    
    private String confirmPassword;
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
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
}
