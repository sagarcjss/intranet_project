package com.cjss.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.PARAMETER,ElementType.FIELD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BooleanValidator.class)
public @interface BooleanCheck {
    public String message() default "Please Enter status as true or false only...";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
