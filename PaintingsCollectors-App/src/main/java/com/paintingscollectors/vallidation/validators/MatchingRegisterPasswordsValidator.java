package com.paintingscollectors.vallidation.validators;

import com.paintingscollectors.model.dto.UserRegisterDto;
import com.paintingscollectors.vallidation.MatchingRegisterPasswords;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MatchingRegisterPasswordsValidator implements ConstraintValidator<MatchingRegisterPasswords, UserRegisterDto> {
    
    @Override
    public boolean isValid(UserRegisterDto dto, ConstraintValidatorContext constraintValidatorContext) {
        String password = dto.getPassword();
        String confirmPassword = dto.getConfirmPassword();
        if (password.equals(confirmPassword)) {
            return true;
        }
        return false;
    }
}
