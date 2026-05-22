package org.example.cursospring.rapidito.api.exception;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FechasValidasValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface FechasValidas {
    String message() default "fechaFin debe ser posterior a fechaInicio";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
