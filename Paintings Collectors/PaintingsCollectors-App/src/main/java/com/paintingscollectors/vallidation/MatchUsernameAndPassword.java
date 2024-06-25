package com.paintingscollectors.vallidation;

import com.paintingscollectors.vallidation.validators.MatchUsernameAndPasswordValidator;
import com.paintingscollectors.vallidation.validators.MatchingRegisterPasswordsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = MatchUsernameAndPasswordValidator.class)
public @interface MatchUsernameAndPassword {
    
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
}
