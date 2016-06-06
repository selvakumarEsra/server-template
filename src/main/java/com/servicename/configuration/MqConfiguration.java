package com.servicename.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

public class MqConfiguration {

    @Autowired
    Environment env;

    @Autowired
    @Bean
    JmsTemplate jmsTemplate(ConnectionFactory connectionFactory){
        return new JmsTemplate();
    }
}
