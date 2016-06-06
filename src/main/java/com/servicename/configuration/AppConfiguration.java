package com.servicename.configuration;

import falcon.easypay.dao.CustomerDataService;
import falcon.easypay.rest.AdminController;
import falcon.easypay.rest.QuotesController;
import falcon.easypay.rest.TransactionsController;
import falcon.easypay.service.QuoteService;
import falcon.easypay.service.QuoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

@Configuration
@Import({
    CacheConfiguration.class,
    DatabaseConfiguration.class,
    MetricsConfiguration.class
})
public class AppConfiguration {

    @Autowired
    Environment env;

    @Bean
    @Autowired
    public QuotesController quotesController(QuoteService quoteService, CustomerDataService customerDataService){
        return new QuotesController(quoteService,customerDataService);
    }

    @Bean
    public TransactionsController transactionsController(){
        return new TransactionsController();
    }

    @Bean
    public AdminController adminController(){
        return new AdminController();
    }

    @Bean
    public QuoteService quoteService(){
        return new QuoteServiceImpl();
    }

}
