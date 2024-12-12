package com.User_Management_Service.User_Management_System_Backend.Validations.Implementation;

import com.User_Management_Service.User_Management_System_Backend.Validations.ValidPhoneNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, String> {

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            // Disable default violation and set custom message for null or empty phone number
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Phone number cannot be null or empty")
                    .addConstraintViolation();
            return false;
        }

        if (phoneNumber.startsWith("0")) {
            if (phoneNumber.length() != 10) {
                // Disable default violation and set custom message for invalid length
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Phone number starting with '0' must be 10 digits long")
                        .addConstraintViolation();
                return false;
            }
        } else if (phoneNumber.startsWith("+94")) {
            if (phoneNumber.length() != 12) {
                // Disable default violation and set custom message for invalid length
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Phone number starting with '+94' must be 12 digits long")
                        .addConstraintViolation();
                return false;
            }
        } else {
            // Disable default violation and set custom message for invalid prefix
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Phone number must start with '0' or '+94'")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
