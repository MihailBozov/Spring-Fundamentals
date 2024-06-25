package com.paintingscollectors.model.dto;

import com.paintingscollectors.vallidation.MatchUsernameAndPassword;

import javax.validation.constraints.Size;

@MatchUsernameAndPassword(message = "Oops, the username or password is not valid")
public class UserLoginDto {
    
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters")
    private String username;
    
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters")
    private String password;
    
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
