package com.cjss.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BooleanValidator implements ConstraintValidator<BooleanCheck, Boolean> {
    @Override
    public boolean isValid(Boolean status, ConstraintValidatorContext context) {
        if (status == null)
            return false;

        return status;
    }
}
