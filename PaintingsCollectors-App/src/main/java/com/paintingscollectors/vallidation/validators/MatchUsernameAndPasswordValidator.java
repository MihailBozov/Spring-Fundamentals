package com.paintingscollectors.vallidation.validators;

import com.paintingscollectors.model.dto.UserLoginDto;
import com.paintingscollectors.model.entity.User;
import com.paintingscollectors.repository.UserRepository;
import com.paintingscollectors.vallidation.MatchUsernameAndPassword;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class MatchUsernameAndPasswordValidator implements ConstraintValidator<MatchUsernameAndPassword, UserLoginDto> {
    private UserRepository userRepository;
    
    @Autowired
    public MatchUsernameAndPasswordValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public boolean isValid(UserLoginDto userLoginDto, ConstraintValidatorContext constraintValidatorContext) {
        Optional<User> user = this.userRepository.findByUsernameAndPassword(userLoginDto.getUsername(), userLoginDto.getPassword());
        
        return user.isPresent();
    }
}
