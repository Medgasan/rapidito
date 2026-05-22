package org.example.cursospring.rapidito.api.exception;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class FechasValidasValidator implements ConstraintValidator<FechasValidas, Object> {

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext ctx) {
        if (obj == null) return true;

        try {
            LocalDate inicio = (LocalDate) obj.getClass().getMethod("getFechaInicio").invoke(obj);
            LocalDate fin = (LocalDate) obj.getClass().getMethod("getFechaFin").invoke(obj);
            if (inicio == null || fin == null) return true;
            return fin.isAfter(inicio);

        } catch (Exception e) {
            return false;
        }
    }
}
