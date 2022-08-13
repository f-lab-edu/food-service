package com.food.common.unit;

import org.junit.jupiter.api.BeforeAll;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class SuperValidationTests<T> {
    protected static Validator validator;

    @BeforeAll
    static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    protected Set<String> failureMessagesOf(T target) {
        return extractMessagesFrom(validator.validate(target));
    }

    protected Set<String> failureMessagesOf(Set<T> targets) {
        Set<String> result = new HashSet<>();

        for (T target : targets) {
            result.addAll(failureMessagesOf(target));
        }

        return result;
    }

    protected Set<String> extractMessagesFrom(Set<ConstraintViolation<T>> constraintViolations) {
        return constraintViolations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toSet());
    }

    protected String formatLength(String failureMessage, int max, String currentValue) {
        return failureMessage
                .replace("{min}", String.valueOf(0))
                .replace("{max}", String.valueOf(max))
                .replace("${validatedValue}", String.valueOf(currentValue));
    }

    protected String formatEmail(String failureMessage, String currentValue) {
        return failureMessage
                .replace("${validatedValue}", currentValue);
    }

    protected String formatPositive(String failureMessage, int currentValue) {
        return failureMessage
                .replace("${validatedValue}", String.valueOf(currentValue));
    }
}
