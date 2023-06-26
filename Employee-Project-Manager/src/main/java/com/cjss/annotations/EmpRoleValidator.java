package com.cjss.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class EmpRoleValidator implements ConstraintValidator<EmpRoleCheck, String> {

    @Override
    public boolean isValid(String role, ConstraintValidatorContext context) {
         if (role == null || role.isBlank())
             return false;
        List<String> rolesList = Arrays.asList("manager", "software developer",
                "UI designers", "QA engineer", "admin", "team lead", "tester");
        Optional<String> optionalRole = rolesList.stream().filter(roles -> roles.equalsIgnoreCase(role))
                .findAny();
        return optionalRole.isPresent();
    }
}
