package com.example.backend.Filters;

import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener() {
        System.out.println("is it here inside?");
        return new ValidatingMongoEventListener(validator());
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        System.out.println("or is it here");
        return new LocalValidatorFactoryBean();
    }
}
