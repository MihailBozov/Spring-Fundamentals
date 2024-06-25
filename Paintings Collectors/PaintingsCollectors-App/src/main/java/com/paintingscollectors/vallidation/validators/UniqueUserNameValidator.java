package com.paintingscollectors.vallidation.validators;

import com.paintingscollectors.model.entity.User;
import com.paintingscollectors.repository.UserRepository;
import com.paintingscollectors.vallidation.UniqueUserName;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class UniqueUserNameValidator implements ConstraintValidator<UniqueUserName, String> {
    
    private UserRepository userRepository;
    
    @Autowired
    public UniqueUserNameValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        Optional<User> user = this.userRepository.findByUsername(username);
        
        if (user.isPresent()) {
            return false;
        }
        return true;
    }
}
