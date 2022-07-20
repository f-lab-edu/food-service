package com.food.common.integration.domain;

import com.food.common.integration.SuperIntegrationTest;
import org.junit.jupiter.api.BeforeAll;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;

public class DomainValidationIntegrationTest extends SuperIntegrationTest {
    protected static ExecutableValidator executableValidator;

    @BeforeAll
    static void setupAll() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        executableValidator = factory.getValidator().forExecutables();
    }
}
