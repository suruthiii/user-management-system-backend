package com.User_Management_Service.User_Management_System_Backend.Validations;

import com.User_Management_Service.User_Management_System_Backend.Validations.Implementation.BirthdayValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = BirthdayValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidBirthday {
    String message() default "Invalid birthday";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
