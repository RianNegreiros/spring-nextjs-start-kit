package xyz.riannegreiros.spring_nextjs_start_kit.util.validators;

import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Constraint(validatedBy = PasswordMatchValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PasswordMatch {

    String message() default "Password do not match";
    
    Class<?>[] groups() default {};
    
    Class<?>[] payload() default {};
    
    String passwordField();
    String passwordConfirmationField();
}
