package com.cjss.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.PARAMETER,ElementType.FIELD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmpRoleValidator.class)
public @interface EmpRoleCheck {
    public String message() default "Enter Valid Employee Role...";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
