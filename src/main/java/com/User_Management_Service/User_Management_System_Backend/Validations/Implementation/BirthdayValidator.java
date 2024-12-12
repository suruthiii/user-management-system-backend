package com.User_Management_Service.User_Management_System_Backend.Validations.Implementation;

import com.User_Management_Service.User_Management_System_Backend.Validations.ValidBirthday;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class BirthdayValidator implements ConstraintValidator<ValidBirthday, LocalDate> {

    @Override
    public boolean isValid(LocalDate birthday, ConstraintValidatorContext context) {
        if (birthday == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Birthday cannot be null")
                    .addConstraintViolation();
            return false;
        }

        if (LocalDate.parse(birthday.toString()).isAfter(LocalDate.now())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Invalid Birthday")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
