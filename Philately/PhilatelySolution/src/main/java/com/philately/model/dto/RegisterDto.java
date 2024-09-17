package com.philately.model.dto;

import com.philately.validation.MatchRegisterPasswords;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@MatchRegisterPasswords //(message = "The passwords do not match")
public class RegisterDto {
    
    private Long id;
    
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters")
    private String username;
    
    @Email(message = "Must contain '@'")
    @NotBlank(message = "Email cannot be empty")
    private String email;
    
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters")
    private String password;

    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters")
    private String confirmPassword;
}
