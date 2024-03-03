package com.udemyspringcourse.restfulwebservices.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DateFormatValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomDateFormatValidation {
    String message() default "date should in yyyy-MM-dd format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
