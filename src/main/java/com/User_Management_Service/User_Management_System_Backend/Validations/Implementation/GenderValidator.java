package com.User_Management_Service.User_Management_System_Backend.Validations.Implementation;

import com.User_Management_Service.User_Management_System_Backend.Validations.ValidGender;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GenderValidator implements ConstraintValidator<ValidGender, String> {

    @Override
    public boolean isValid(String gender, ConstraintValidatorContext context) {
        if (gender == null || gender.isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Gender cannot be null or empty")
                    .addConstraintViolation();
            return false;
        }

        if (!gender.equals("MALE") && !gender.equals("FEMALE")) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Invalid gender type")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
