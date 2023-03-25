package com.parade.paradeproject.util.validationUtil;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SqlInjectValidator.class)
public @interface SqlInjectValid {


    String message() default "forbidden word exist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
