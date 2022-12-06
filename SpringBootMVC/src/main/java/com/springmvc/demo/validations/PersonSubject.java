package com.springmvc.demo.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PersonSubjectValidator.class)
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PersonSubject {
//making random validation to test out functionality
    String value() default "nosubject";
    String message() default "Broken rule";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
