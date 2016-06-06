package com.servicename.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

@Configuration
@PropertySources({
    @PropertySource(value = "classpath:/application.properties"),
        @PropertySource(value = "classpath:/environment.properties")
})
public class PropertiesConfiguration {

    @Autowired
    Environment environment;
}
