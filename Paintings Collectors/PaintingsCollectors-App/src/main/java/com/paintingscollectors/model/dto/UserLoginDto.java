package com.paintingscollectors.model.dto;

import com.paintingscollectors.vallidation.MatchUsernameAndPassword;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;
@Getter
@Setter
@NoArgsConstructor
@MatchUsernameAndPassword(message = "Oops, the username or password is not valid")
public class UserLoginDto {
    
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters")
    private String username;
    
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters")
    private String password;

}
