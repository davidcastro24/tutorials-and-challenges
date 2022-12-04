package com.socialapp.javareact.users;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueUserNameValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUserName {
    String message() default "{javareact.constraints.username.inUse.Message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
