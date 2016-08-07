package ru.cg.providerCRM.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {UniqueEmployeeNameValidator.class})
public @interface UniqueEmployeeName {
    String message() default "name already exist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
