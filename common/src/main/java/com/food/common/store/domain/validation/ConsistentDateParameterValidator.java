package com.food.common.store.domain.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import java.time.LocalTime;

@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class ConsistentDateParameterValidator implements ConstraintValidator<ConsistentDateParameters, Object[]> {

    @Override
    public boolean isValid(Object[] value, ConstraintValidatorContext context) {
        Object openingTime = value[3];
        Object closingTime = value[4];

        if (openingTime == null || closingTime == null) {
            return true;
        }

        if (!(openingTime instanceof LocalTime)
                || !(closingTime instanceof LocalTime)) {
            throw new IllegalArgumentException(
                    "Illegal method signature, expected two parameters of type LocalDate.");
        }

        return ((LocalTime) openingTime).isBefore((LocalTime) closingTime);
    }
}
