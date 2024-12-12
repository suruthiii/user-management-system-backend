package com.User_Management_Service.User_Management_System_Backend.Validations.Implementation;

import com.User_Management_Service.User_Management_System_Backend.Validations.ValidEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null || email.isEmpty()) {
            // Disable default violation and set custom message for null or empty email
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Email cannot be null or empty")
                    .addConstraintViolation();
            return false;
        }

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            // Disable default violation and set custom message for invalid email format
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Invalid email format")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
