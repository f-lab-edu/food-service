package com.food.common.integration;

import com.food.common.config.JpaAuditConfig;
import com.food.common.integration.mockFactory.UserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

@Import({
        UserFactory.class
})
@DataJpaTest(includeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = JpaAuditConfig.class
))
public class SuperIntegrationTest {

    @Autowired
    protected UserFactory userFactory;
}
