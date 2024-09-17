package com.bonappetit.vallidation.validators;

import com.bonappetit.model.dto.LoginDto;
import com.bonappetit.model.entity.User;
import com.bonappetit.repo.UserRepository;
import com.bonappetit.vallidation.LoginEmailAndPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class LoginEmailAndPasswordValidator implements ConstraintValidator<LoginEmailAndPassword, LoginDto> {
    
    private final UserRepository userRepository;

    @Autowired
    public LoginEmailAndPasswordValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(LoginDto loginDto, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("username: " + loginDto.getUsername() + "; passsword: " + loginDto.getPassword() );
        Optional<User> user = this.userRepository.findUserByUsernameAndPassword(loginDto.getUsername(), loginDto.getPassword());
        return user.isPresent();
    }
}
