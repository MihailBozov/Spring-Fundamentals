package com.bonappetit.model.dto;

import com.bonappetit.vallidation.MatchRegisterPasswords;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@MatchRegisterPasswords(message = "The passwords do not match")
public class RegisterDto {
    
    private Long id;
    
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters (inclusive of 3 and 20).")
    private String username;
    
    @Email(message = "Must contain '@'.")
    @Size(min = 1, message = "Must contain '@'.")
    private String email;
    
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters (inclusive of 3 and 20).")
    private String password;
    
    private String confirmPassword;
}
