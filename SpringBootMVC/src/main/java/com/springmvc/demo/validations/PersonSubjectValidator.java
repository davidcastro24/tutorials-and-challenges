package com.springmvc.demo.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PersonSubjectValidator implements ConstraintValidator<PersonSubject,String> {

    private String subjectValue;

    @Override
    public void initialize(PersonSubject personSubject) {
        subjectValue = personSubject.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null)
            return false;
        return value.toLowerCase().equals(subjectValue);
    }
}
