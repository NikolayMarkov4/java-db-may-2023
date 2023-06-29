package com.example.usersystem.annotations;

import jakarta.validation.ConstraintValidatorContext;

public enum AnnotationsUtil {
    ;

    public static void setErrorMessage(final ConstraintValidatorContext context, final String errorMessage) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(errorMessage).addConstraintViolation();
    }
}