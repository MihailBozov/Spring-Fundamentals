package com.philately.validation.validators;


import com.philately.model.dto.RegisterDto;
import com.philately.validation.MatchRegisterPasswords;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MatchRegisterPasswordsValidator implements ConstraintValidator<MatchRegisterPasswords, RegisterDto> {
    
    @Override
    public boolean isValid(RegisterDto registerDto, ConstraintValidatorContext constraintValidatorContext) {
        String password = registerDto.getPassword();
        String confirmPassword = registerDto.getConfirmPassword();
        
        return password.equals(confirmPassword);
    }
}
