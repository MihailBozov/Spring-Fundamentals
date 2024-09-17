package com.bonappetit.model.dto;

import com.bonappetit.vallidation.LoginEmailAndPassword;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@LoginEmailAndPassword(message = "Incorrect username or password!")
public class LoginDto {

    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters (inclusive of 3 and 20).")
    private String username;
    
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters (inclusive of 3 and 20).")
    private String password;
}
